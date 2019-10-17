package com.regnquiz.controller;

import com.regnquiz.model.AccessCode;
import com.regnquiz.model.Booking;
import com.regnquiz.model.LectureRun;
import com.regnquiz.model.repositories.BookingQuestionRepository;
import com.regnquiz.model.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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
        HttpSession session = request.getSession(false);
        try {
            model.addAttribute("booking", bookings.get(session.getAttribute("booking")).getBooking());
            return "attendBooking";
        }catch (NullPointerException e){
            return "accessBooking";
        }
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
        if(bookings.get(id).getQuizFinished()){
            model.addAttribute("finished", 1);
        }
        System.out.println("Size "+runningBookings.size());
        return "booking";
    }

    @PostMapping(path = "/{id}/start")
    public String startQuestions(@PathVariable("id") int id, Model model, HttpServletRequest request){
        LectureRun lectureRun = bookings.get(id);
        lectureRun.startQuestion();
        model.addAttribute("booking", lectureRun.getBooking());
        model.addAttribute("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
        model.addAttribute("multipleChoices", lectureRun.getMultipleChoice(lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID()));
        model.addAttribute("questionid", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID());
        return "booking";
    }

    @PostMapping(path = "/{id}/next/{qid}")
    public String nextQuestions(@PathVariable("id") int id, @PathVariable("qid") int qid, Model model, HttpServletRequest request){
        LectureRun lectureRun = bookings.get(id);
        lectureRun.nextQuestion();
        if(lectureRun.getBookingQuestions().size() != lectureRun.getActiveQuestion()) {
            model.addAttribute("booking", lectureRun.getBooking());
            model.addAttribute("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
            model.addAttribute("multipleChoices", lectureRun.getMultipleChoice(lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID()));
            model.addAttribute("questionid", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID());
        }else{
            model.addAttribute("finished", 1);

        }
        return "booking";
    }

    @PostMapping(path = "/getquestion")
    public ModelAndView getQuestions(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        ModelAndView mv= new ModelAndView("attendBooking::questionList");

        LectureRun lectureRun = bookings.get(session.getAttribute("booking"));
        mv.addObject("booking", lectureRun.getBooking());

        if(lectureRun.getActiveQuestion() >= 0) {
            System.out.println("ACTIVE QUESTION "+lectureRun.getActiveQuestion());
            mv.addObject("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
            mv.addObject("multipleChoices", lectureRun.getMultipleChoice(lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID()));
        }
        return mv;
    }

    @GetMapping(path="/attend")
    public String attendBooking(@ModelAttribute("accessCode") AccessCode code, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        int bookingID = -1;
        try{
            bookingID = runningBookings.get(code.getAccessCode());
        }catch (NullPointerException e){
            model.addAttribute("nobooking", 1);
            return "accessBooking";
        }

        if(bookings.get(bookingID).getStudent((Integer) session.getAttribute("userID")).isAttendance() == false) {
            bookings.get(bookingID).setAttendance((Integer) session.getAttribute("userID"));
        }
        session.setAttribute("booking", bookingID);
        model.addAttribute("booking", bookings.get(bookingID).getBooking());
        return "attendBooking";
    }

    @PostMapping(path="/getattendance")
    public @ResponseBody int attendanceCount(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        try{
            return bookings.get(bookingID).getAttendanceCount();
        }catch (NullPointerException e){
            return -1;
        }
    }

    @GetMapping(path = "/addquestion")
    public String addQuestion(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("bookings", bookingRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "bookingQuestion";
    }
}
