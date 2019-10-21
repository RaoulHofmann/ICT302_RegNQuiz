package com.regnquiz.controller;

import com.regnquiz.model.*;
import com.regnquiz.model.repositories.BookingQuestionRepository;
import com.regnquiz.model.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
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
        if(bookings.get(id).getQuizFinished()){
            model.addAttribute("finished", 1);
        }else{
            model.addAttribute("booking", bookings.get(id).getBooking());
        }
        return "booking";
    }

    @PostMapping(path = "/{id}/question")
    public String startQuestions(@PathVariable("id") int id, Model model, HttpServletRequest request){
        LectureRun lectureRun = bookings.get(id);
        lectureRun.startQuestion();
        model.addAttribute("booking", lectureRun.getBooking());
        model.addAttribute("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
        model.addAttribute("multipleChoices", lectureRun.getMultipleChoice());
        model.addAttribute("questionid", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID());
        model.addAttribute("timer", lectureRun.getQuestionTimer());
        return "booking";
    }

    @PostMapping(path = "/{id}/question/{qid}")
    public String nextQuestions(@PathVariable("id") int id, @PathVariable("qid") int qid, Model model, HttpServletRequest request){
        LectureRun lectureRun = bookings.get(id);
        if(lectureRun.getActiveQuestion() < lectureRun.getBookingQuestions().size()-1) {
            lectureRun.nextQuestion();
            model.addAttribute("booking", lectureRun.getBooking());
            model.addAttribute("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
            model.addAttribute("multipleChoices", lectureRun.getMultipleChoice());
            model.addAttribute("questionid", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion().getQID());
            model.addAttribute("timer", lectureRun.getQuestionTimer());
        }else{
            //lectureRun.saveLecture();
            System.out.println("FINISHED!! "+id);
            model.addAttribute("booking_id", id);
            model.addAttribute("finished", 1);
        }
        return "booking";
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
        model.addAttribute("answer", new Answer());

        return "attendBooking";
    }

    @PostMapping(path = "/getquestion")
    public ModelAndView getQuestions(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        ModelAndView mv= new ModelAndView("attendBooking::questionList");

        LectureRun lectureRun = bookings.get(session.getAttribute("booking"));
        mv.addObject("booking", lectureRun.getBooking());
        mv.addObject("answer", new Answer());

        try{
            if(lectureRun.getActiveQuestion() >= 0 && lectureRun.getActiveQuestion() > ((Integer)session.getAttribute("lastQuestion")) && !lectureRun.getQuizFinished()) {
                mv.addObject("questions", lectureRun.getBookingQuestions().get(lectureRun.getActiveQuestion()).getQuestion());
                mv.addObject("multipleChoices", lectureRun.getMultipleChoice());
                mv.addObject("timer", lectureRun.getQuestionTimer());
                session.setAttribute("lastQuestion", lectureRun.getActiveQuestion());
            }else if(lectureRun.getQuizFinished()) {
                session.removeAttribute("lastQuestion");
                session.removeAttribute("booking");
                model.addAttribute("finished", "Quiz is Finished");
                return new ModelAndView("attendBooking::finishedQuiz");
            }else{
                model.addAttribute("waiting", 1);
                return new ModelAndView("attendBooking::waitingForChange");
            }
        }catch (NullPointerException e){
            session.setAttribute("lastQuestion", -1);
        }

        return mv;
    }

    @PostMapping(path="/getattendance")
    public @ResponseBody int attendanceCount(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        try{
            return bookings.get(bookingID).getAttendanceCount();
        }catch (NullPointerException e){
            return -1;
        }
    }

    @PostMapping(path="/getstudentanswers")
    public ModelAndView studentAnswer(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        model.addAttribute("studentAnswers", bookings.get(bookingID).getAnswerCounter());
        return new ModelAndView("booking::studentAnswer");
    }

    @GetMapping(path = "/addquestion")
    public String addQuestion(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("bookings", bookingRepository.findByLecture_userID((Integer)session.getAttribute("userID")));
        return "bookingQuestion";
    }

    @PostMapping(value = "/answer")
    public String studentAnswer(@Valid Answer answer, BindingResult result, ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        LectureRun lectureRun = bookings.get(session.getAttribute("booking"));
        lectureRun.setStudentAnswer(answer.getAnswerID(), (Integer)session.getAttribute("userID"));
        lectureRun.setQuestionAnswerCounter(answer.getAnswerID());
        model.addAttribute("booking", lectureRun.getBooking());
        model.addAttribute("waiting", 1);
        return "attendBooking";
    }

    @PostMapping(value ="/settimeout")
    public @ResponseBody void setTimeout(@RequestParam int timer, @RequestParam int bookingID, Model model, HttpServletRequest request){
        if(timer == 1){
            HttpSession session = request.getSession();

            LectureRun lectureRun = bookings.get(bookingID);
            lectureRun.setTimeout();
        }
    }

    @PostMapping(value ="/gettimeout")
    public @ResponseBody int getTimeout(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        LectureRun lectureRun = bookings.get(session.getAttribute("booking"));
        if(lectureRun.getTimeout() == true){
            return 1;
        }else{
            return 0;
        }
    }

    @PostMapping(path="/getanswerattendance")
    public @ResponseBody List<Integer> attendanceAnswerCount(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        List<Integer> answerAttendance = new ArrayList<>();
        try{
            answerAttendance.add(bookings.get(bookingID).getAttendanceCount());
            answerAttendance.add(bookings.get(bookingID).getAnswerCounter());
        }catch (NullPointerException e){
            answerAttendance.add(-1);
        }
        return answerAttendance;
    }

    @PostMapping(path="/getquestionoverview")
    public @ResponseBody HashMap<String, HashMap<String, Integer>> questionAnswerOverview(@RequestParam int bookingID, Model model, HttpServletRequest request) {
        return bookings.get(bookingID).getQuestionAnswerCounter();
    }

}
