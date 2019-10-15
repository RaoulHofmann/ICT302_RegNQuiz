package com.regnquiz.controller;

import com.regnquiz.model.AccessCode;
import com.regnquiz.model.Booking;
import com.regnquiz.model.LectureRun;
import com.regnquiz.model.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LectureRun lectureRun;

    private HashMap<Integer, LectureRun> bookings;
    @Autowired
    public void setBookingMap(HashMap<Integer, LectureRun> bookings) {
        this.bookings = bookings;
    }

    private HashMap<String, Integer> runningBookings;
    @Autowired
    public void setRunningBookingsMap(HashMap<String, Integer> running) {
        this.runningBookings = running;
    }

    @GetMapping(path = "/")
    public String booking(Model model, HttpServletRequest request) {
        return "bookingOverview";
    }

    @GetMapping(path = "/join")
    public String joinBooking(Model model, HttpServletRequest request) {
        return "accessBooking";
    }

    @PostMapping(path = "/status")
    public @ResponseBody Integer bookingStatus(@RequestParam int bookingID){
        try{
            return bookings.get(bookingID).isActive();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @GetMapping(path = "/{id}")
    public String startBooking(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        lectureRun.OpenLecture(id);
        bookings.put(id, lectureRun);
        runningBookings.put(lectureRun.getAccessCode(), id);
        model.addAttribute("booking", bookings.get(id).getBooking());
        System.out.println("Size "+runningBookings.size());
        return "booking";
    }

    @GetMapping(path="/attend")
    public String attendBooking(@ModelAttribute("accessCode") AccessCode code, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int bookingID = runningBookings.get(code.getAccessCode());
        bookings.get(bookingID).setAttendance((Integer)session.getAttribute("userID"));
        model.addAttribute("booking", bookings.get(bookingID).getBooking());
        return "attendBooking";
    }

    @PostMapping(path="/getattendance")
    public @ResponseBody int attendanceCount(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        return bookings.get(bookingID).getAttendanceCount();
    }

    @GetMapping(path = "/addquestion")
    public String addQuestion(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("bookings", bookingRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "bookingQuestion";
    }
}
