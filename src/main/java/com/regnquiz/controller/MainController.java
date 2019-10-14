
package com.regnquiz.controller;

import com.regnquiz.model.*;
import com.regnquiz.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    private UserTypeQueryRepository userTypeQueryRepository;
    @Autowired
    private BookingRepository bookingRepository;

    private Map<Integer, Booking> bookings;
    @Autowired
    public void setBookingMap(Map<Integer, Booking> bookings) {
        this.bookings = bookings;
    }

    private Map<Integer, Integer> runningBookings;
    @Autowired
    public void setRunningBookingsMap(Map<Integer, Integer> running) {
        this.runningBookings = running;
    }

    @GetMapping(path = "/gettypes")
    public @ResponseBody
    Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @PostMapping(path = "/usertypes")
    public @ResponseBody
    Type getUserTypes(@RequestParam int userid) {
        return userTypeQueryRepository.getUserTypes(userid);
    }

    @GetMapping(path = "/getuser")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path = "/addtype")
    public @ResponseBody
    String addNewType(@RequestParam String desc) {

        Type t = new Type();
        t.setDescription(desc);
        System.out.println("Description: " + t.getDescription());

        try {
            typeRepository.save(t);
        } catch (DataIntegrityViolationException ex) {
            return "Data Integrity Violation Exception" + ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path = "/addusertype") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUserType(@RequestParam String desc) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Type t = new Type();
        t.setDescription(desc);
        System.out.println("Description: " + t.getDescription());

        try {
            typeRepository.save(t);
        } catch (DataIntegrityViolationException ex) {
            return "Data Integrity Violation Exception" + ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path = "/adduser") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setGivenName(name);
        System.out.println("Name: " + n.getGivenName());

        try {
            userRepository.save(n);
        } catch (DataIntegrityViolationException ex) {
            return "Data Integrity Violation Exception" + ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLogin(@ModelAttribute("login") Login login, Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid Login login, BindingResult result, ModelMap model, HttpServletRequest request) {
        int userType = -1;
        int userID = -1;
        try {
            userType = userTypeQueryRepository.getUserTypes(login.getUserID()).getTypeID();
            userID = userRepository.findById(login.getUserID()).get().getUserID();
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            session.setAttribute("userID", userID);
            System.out.println("Session Login " + session.getId());
        } catch (InvalidDataAccessResourceUsageException e) {
            return new ModelAndView("redirect:/");
        }

        model.addAttribute("id", login.getUserID());

        if (userType == 2) {
            return new ModelAndView("redirect:/staff/{id}", model);
        } else if (userType == 3) {
            return new ModelAndView("redirect:/student/{id}", model);
        } else {
            return new ModelAndView("redirect:/");
        }
        //return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/user").build();
    }

    @PostMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!session.getId().isEmpty()) {
            session.invalidate();
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(path = "/getbooking")
    public @ResponseBody List<Booking> getbooking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return bookingRepository.findByLecture_userID((Integer)session.getAttribute("userID"));
    }

    @GetMapping(path = "/staff/{id}")
    public String goToStaffIndex(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            if (request.getSession() != null && (Integer) request.getSession().getAttribute("userID") == id) {
                model.addAttribute("user", userRepository.findById(id).get());
                return "staff";
            } else {
                return "redirect:/";
            }
        } catch (NullPointerException e) {
            return "redirect:/";
        }
    }

    @GetMapping(path = "/student/{id}")
    public String goToStudentIndex(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            if (request.getSession() != null && (Integer) request.getSession().getAttribute("userID") == id) {
                model.addAttribute("user", userRepository.findById(id).get());
                return "student";
            } else {
                return "redirect:/";
            }
        } catch (NullPointerException e) {
            return "redirect:/";
        }
    }

    @GetMapping(path = "/startbooking/{id}")
    public String startBooking(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        System.out.println("START BOOKING "+id);
        Booking booking = bookingRepository.findById(id).get();
        bookings.put(id, booking);
        runningBookings.put(Integer.parseInt(booking.getAttendanceCode()), id);
        model.addAttribute("booking", bookings.get(id));
        return "booking";
    }

    @GetMapping(path="/booking/")
    public String booking(@ModelAttribute("command") AccessCode code, Model model, HttpServletRequest request) {
        System.out.println("TESTING BOOKING"+bookings.size());
        try {
            int bookingID = runningBookings.get(code.getAccessCode());
            model.addAttribute("booking", bookings.get(bookingID));
            return "attendBooking";
        }catch (NullPointerException e){
            return "redirect:/";
        }
    }
}
