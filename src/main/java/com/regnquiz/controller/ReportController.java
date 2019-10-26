package com.regnquiz.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.regnquiz.model.AttendanceReview;
import com.regnquiz.model.Booking;
import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.BookingReport;
import com.regnquiz.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path="/report")
public class ReportController {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AttendanceReviewRepository attendanceReviewRepository;

    @Autowired
    private QuestionReviewRepository questionReviewRepository;

    @GetMapping(path = "/lecture")
    public String lecturerReportOverview(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        model.addAttribute("units", unitRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "lectureReport";
    }

    @PostMapping(path = "/getunitreport")
    public ModelAndView getAttendance(@RequestParam int unitID, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("attendanceReviews", attendanceReviewRepository.findAllByUnitID(unitID));
            model.addAttribute("questionReviews", questionReviewRepository.findAllByUnitID(unitID));
        }catch (NoSuchElementException e){
            model.addAttribute("empty", 1);
        }
        return new ModelAndView("lectureReport::reportTable");
    }

    @GetMapping(path = "/csv/attendance/{unitID}", produces = { "text/csv" })
    public void bookingCSVExport(@PathVariable("unitID") int unitID, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String filename = "booking_"+unitID+"_question_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");


        StatefulBeanToCsv<AttendanceReview> writer = new StatefulBeanToCsvBuilder<AttendanceReview>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        List<AttendanceReview> reports = new ArrayList<>();

        reports = attendanceReviewRepository.findAllByUnitID(unitID);

        writer.write(reports);
    }
}
