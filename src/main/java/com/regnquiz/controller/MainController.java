
package com.regnquiz.controller;

import com.regnquiz.model.Booking;
import com.regnquiz.model.Type;
import com.regnquiz.model.User;
import com.regnquiz.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.net.URI;
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

    /*@PostMapping(path="/login")
    public ModelAndView login(@RequestParam int userid, @RequestParam int password, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        //User user = UserDetailService.loadUserByUserid(auth.getName());
        //modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("/");
        System.out.println("ASDASDASD");
        return modelAndView;
    }*/

    @PostMapping(path="/login")
    public @ResponseBody
    ResponseEntity<Void> login(@RequestParam int userid, @RequestParam int password, HttpServletRequest request) {
        List<Object> user_info = new ArrayList<>();
        try {
            int userType = userTypeQueryRepository.getUserTypes(userid).getTypeID();
            int userID = userRepository.findById(userid).get().getUserID();
            HttpSession session = request.getSession();
            session.setAttribute("userType",userType);
            session.setAttribute("userID",userID);
            System.out.println("Session Login "+ session.getId());
            user_info.add(userType);
            user_info.add(userID);
            user_info.add(userRepository.findById(userid).get().getGivenName());
            user_info.add(userRepository.findById(userid).get().getLastName());
            user_info.add(userRepository.findById(userid).get().getPrefName());
        }catch(InvalidDataAccessResourceUsageException e){
            user_info.add(-2);
        }
        //return user_info;
        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/user/{id}").build()
                .expand(user_info.get(0)).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        ResponseEntity<Void> entity = new ResponseEntity<Void>(headers,
                HttpStatus.CREATED);
        return entity;
        //return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/user").build();
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
    public @ResponseBody String gotoStaff(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        System.out.println("SADJASHDJAKSBDOAJSKD");
        return "staff";
    }

    @GetMapping(path="/checksession")
    public @ResponseBody String checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null){
            if(session.getAttribute("userType").equals(2)){
                return "staff";
            }else if(session.getAttribute("userType").equals(2)){
                return "student";
            }
        }
        return "inValid";
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody String userCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null){
            if(session.getAttribute("userType").equals(2)){
                return "staff";
            }else if(session.getAttribute("userType").equals(2)){
                return "student";
            }
        }
        return "inValid";
    }
}