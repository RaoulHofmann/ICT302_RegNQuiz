package com.regnquiz.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.regnquiz.model.*;
import com.regnquiz.model.forms.VenueReportForm;
import com.regnquiz.model.repositories.*;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path="/report")
public class ReportController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingQuestionRepository bookingQuestionRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AttendanceReviewRepository attendanceReviewRepository;

    @Autowired
    private QuestionReviewRepository questionReviewRepository;

    @Autowired
    private StudentAnswerReviewRepository studentAnswerReviewRepository;

    @Autowired
    private ClassListRepository classListRepository;

    @Autowired
    private VenueReviewRepository venueReviewRepository;

    @GetMapping(path = "/lecture")
    public String lecturerReportOverview(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        model.addAttribute("units", unitRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "lectureReport";
    }

    @GetMapping(path = "/admin")
    public String adminReportOverview(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("venueReport", new VenueReportForm());
        return "adminReport";
    }

    @PostMapping(path = "/getfloors")
    public ModelAndView getFloors(@RequestParam int building, Model model, HttpServletRequest request) {
        model.addAttribute("floors", venueRepository.findByBuilding(building));
        model.addAttribute("venueReport", new VenueReportForm());
        return new ModelAndView("adminReport::floorList");
    }

    @PostMapping(path = "/getrooms")
    public ModelAndView getRooms(@RequestParam int building, @RequestParam int floor, Model model, HttpServletRequest request) {
        model.addAttribute("rooms", venueRepository.findByBuildingAndFloor(building, floor));
        model.addAttribute("venueReport", new VenueReportForm());
        return new ModelAndView("adminReport::roomList");
    }

    @PostMapping(path = "/getdates")
    public ModelAndView getDates(@RequestParam int building, Model model, HttpServletRequest request) {
        model.addAttribute("vdates", venueReviewRepository.findByBuilding(building));
        model.addAttribute("venueReport", new VenueReportForm());
        return new ModelAndView("adminReport::dateList");
    }

    @GetMapping(path = "/student")
    public String studentReportOverview(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        List<Booking> bookings = new ArrayList<>();
        for(int i=0; i<classListRepository.findByUserIDWhereAttendance((Integer)session.getAttribute("userID")).size(); i++) {
              bookings.add(bookingRepository.findById(classListRepository.findByUserIDWhereAttendance((Integer) session.getAttribute("userID")).get(i).getBooking().getBookingID()).get());
        }
        model.addAttribute("answerReviews", bookings);
        return "studentReport";
    }

    @PostMapping(path = "/getattreport")
    public ModelAndView getAttendanceReview(@RequestParam int unitID, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("attendanceReviews", attendanceReviewRepository.findByUnitID(unitID));
            List<Booking> bookings = new ArrayList<>();
            for(int i=0; i<attendanceReviewRepository.findByUnitID(unitID).size(); i++){
                bookings.add(bookingRepository.findByUnit_unitIDAndDate(attendanceReviewRepository.findByUnitID(unitID).get(i).getUnitID(), attendanceReviewRepository.findByUnitID(unitID).get(i).getDate()));
            }
            model.addAttribute("bookings", bookings);
        }catch (NoSuchElementException e){
            model.addAttribute("empty", 1);
        }
        return new ModelAndView("lectureReport::attendanceReport");
    }

    @PostMapping(path = "/getquereport")
    public ModelAndView getQuestionReview(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("booking", bookingRepository.findById(bookingID).get());
        }catch (NoSuchElementException e){
            model.addAttribute("empty", 1);
        }
        return new ModelAndView("lectureReport::questionReport");
    }

    @GetMapping(path = "/csv/attendance/{unitID}/{date}", produces = { "text/csv" })
    public void bookingAttendanceCSVExport(@PathVariable("unitID") int unitID, @PathVariable("date") String sdate, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
        String filename = "unit_"+unitID+"_attendance_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");


        StatefulBeanToCsv<AttendanceReview> writer = new StatefulBeanToCsvBuilder<AttendanceReview>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();

        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(sdate);
        List<AttendanceReview> reports = attendanceReviewRepository.findByUnitIDAndDate(unitID, date);

        writer.write(reports);
    }

    @GetMapping(path = "/csv/questionreview/{bookingID}", produces = { "text/csv" })
    public void bookingQuestionCSVExport(@PathVariable("bookingID") int bookingID, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
        String filename = "booking_"+bookingID+"_question_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        HeaderColumnNameMappingStrategy<QuestionReview> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(QuestionReview.class);
        StatefulBeanToCsv<QuestionReview> writer = new StatefulBeanToCsvBuilder<QuestionReview>(response.getWriter())
                .withMappingStrategy(strategy)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(false)
                .build();

        writer.write(questionReviewRepository.findByBookingID_OrderByQuestionAsc(bookingID));
    }

    @GetMapping(path = "/csv/studentanswer/{unitID}/{date}", produces = { "text/csv" })
    public void studentAnswerCSVExport(@PathVariable("unitID") int unitID, Model model, @PathVariable("date") String sdate,HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
        HttpSession session = request.getSession(false);

        String filename = ""+unitID+"_question_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        HeaderColumnNameMappingStrategy<StudentAnswerReview> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(StudentAnswerReview.class);
        StatefulBeanToCsv<StudentAnswerReview> writer = new StatefulBeanToCsvBuilder<StudentAnswerReview>(response.getWriter())
                .withMappingStrategy(strategy)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(false)
                .build();

        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(sdate);
        writer.write(studentAnswerReviewRepository.findByUserIDAndUnitIDAndBookingDate((Integer)session.getAttribute("userID"), unitID, date));
    }

    @PostMapping(path = "/csv/adminvenuereport", produces = { "text/csv"})
    public void generateVenueReport(@Valid VenueReportForm venueReport, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        System.out.println("GENERATING REPORT.......");

        String filename = ""+venueReport.getBuilding()+"_venue_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        HeaderColumnNameMappingStrategy<VenueReview> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(VenueReview.class);
        StatefulBeanToCsv<VenueReview> writer = new StatefulBeanToCsvBuilder<VenueReview>(response.getWriter())
                .withMappingStrategy(strategy)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                .withOrderedResults(false)
                .build();

        System.out.println("DATE: "+venueReport.getDate());
        if(venueReport.getDate() != null){
            if(venueReport.getFloor() != null){
                if(venueReport.getRoom() != null){
                    writer.write(venueReviewRepository.findByBuildingAndFloorAndRoomAndDate(venueReport.getBuilding(), venueReport.getFloor(), venueReport.getRoom(), venueReport.getDate()));
                }else{
                    writer.write(venueReviewRepository.findByBuildingAndFloorAndDate(venueReport.getBuilding(), venueReport.getFloor(), venueReport.getDate()));
                }
            }else{
                writer.write(venueReviewRepository.findByBuildingAndDate(venueReport.getBuilding(), venueReport.getDate()));
            }
        }else{
            if(venueReport.getFloor() != null){
                if(venueReport.getRoom() != null){
                    writer.write(venueReviewRepository.findByBuildingAndFloorAndRoom(venueReport.getBuilding(), venueReport.getFloor(), venueReport.getRoom()));
                }else{
                    writer.write(venueReviewRepository.findByBuildingAndFloor(venueReport.getBuilding(), venueReport.getFloor()));
                }
            }else{
                writer.write(venueReviewRepository.findByBuilding(venueReport.getBuilding()));
            }
        }

        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("vdates", venueReviewRepository.findAll());
        model.addAttribute("venueReport", new VenueReport());
    }
}
