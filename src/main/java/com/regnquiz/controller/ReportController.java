package com.regnquiz.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.regnquiz.model.Booking;
import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.BookingReport;
import com.regnquiz.model.repositories.BookingQuestionRepository;
import com.regnquiz.model.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/report")
public class ReportController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingQuestionRepository bookingQuestionRepository;

    @GetMapping(path = "/lecture")
    public String lecturerReportOverview(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        model.addAttribute("bookings", bookingRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "lectureReport";
    }

    @GetMapping(path = "/csv/booking/{bookingID}", produces = { "text/csv" })
    public void bookingCSVExport(@PathVariable("bookingID") int bookingID, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String filename = "booking_"+bookingID+"_question_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");


        StatefulBeanToCsv<BookingReport> writer = new StatefulBeanToCsvBuilder<BookingReport>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        List<BookingReport> reports = new ArrayList<>();

        for(int i = 0; i<bookingQuestionRepository.findByBooking_BookingID(bookingID).size(); i++){
            reports.add(new BookingReport(
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getBooking().getDate(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getBooking().getTime(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getBooking().getVenue().getBuilding(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getBooking().getVenue().getFloor(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getBooking().getVenue().getRoom(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getQuestion().getQuestionID(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getQuestion().getDescription(),
                            bookingQuestionRepository.findByBooking_BookingID(bookingID).get(i).getQuestion().getAnswer())
                        );
        }

        writer.write(reports);
    }
}
