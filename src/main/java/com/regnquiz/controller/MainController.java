
package com.regnquiz.controller;

import com.regnquiz.model.Booking;
import com.regnquiz.model.Type;
import com.regnquiz.model.User;
import com.regnquiz.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/")
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
    private BookingRepository BookingRepository;

    @GetMapping(path="/gettypes")
    public @ResponseBody Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @PostMapping(path="/usertypes")
    public @ResponseBody Type getUserTypes(@RequestParam int userid) {
        return userTypeQueryRepository.getUserTypes(userid);
    }

    @GetMapping(path="/getuser")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/addtype")
    public @ResponseBody String addNewType (@RequestParam String desc){

        Type t = new Type();
        t.setDescription(desc);
        System.out.println("Description: "+t.getDescription());

        try {
            typeRepository.save(t);
        }catch(DataIntegrityViolationException ex){
            return "Data Integrity Violation Exception"+ ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path="/addusertype") // Map ONLY POST Requests
    public @ResponseBody String addNewUserType (@RequestParam String desc){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Type t = new Type();
        t.setDescription(desc);
        System.out.println("Description: "+t.getDescription());

        try {
            typeRepository.save(t);
        }catch(DataIntegrityViolationException ex){
            return "Data Integrity Violation Exception"+ ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path="/adduser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setGivenName(name);
        System.out.println("Name: "+n.getGivenName());

        try {
            userRepository.save(n);
        }catch(DataIntegrityViolationException ex){
            return "Data Integrity Violation Exception"+ ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path="/login")
    public @ResponseBody List<Object> login(@RequestParam int userid, @RequestParam int password) {
        List<Object> user_info = new ArrayList<>();
        try {
            int userType = userTypeQueryRepository.getUserTypes(userid).getTypeID();
            int userID = userRepository.findById(userid).get().getUserID();
            /*HttpSession session = request.getSession();
            session.setAttribute("userType",userType);
            session.setAttribute("userID",userID);
            System.out.println("Session Login "+ session.getId());*/
            user_info.add(userType);
            user_info.add(userID);
            user_info.add(userRepository.findById(userid).get().getGivenName());
            user_info.add(userRepository.findById(userid).get().getLastName());
            user_info.add(userRepository.findById(userid).get().getPrefName());
        }catch(InvalidDataAccessResourceUsageException e){
            user_info.add(-2);
        }
        return user_info;
    }

    @PostMapping(path="/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println("LOGGED OUT");
        if (!session.getId().isEmpty()) {
            session.invalidate();
        }
        System.out.println("END!");
        return new RedirectView("/");
    }

    @PostMapping(path="/getbooking")
    public @ResponseBody List<Booking> getbooking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return BookingRepository.findByUserID((Integer)session.getAttribute("userID"));
    }

    @GetMapping(path="/student")
    public String gotoStudent(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/student.html";
    }

    @GetMapping(path="/staff")
    public String gotoStaff(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        System.out.println("SADJASHDJAKSBDOAJSKD");
        //redirectAttributes.addAttribute("user_info", user_info);
        redirectAttributes.addFlashAttribute("user_info", "Something");
        return "staff";
        /*List<Object> user_info = new ArrayList<>();
        user_info.add(request);
        return  user_info;*/
    }
}