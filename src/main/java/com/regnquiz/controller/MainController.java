
package com.regnquiz.controller;

import com.regnquiz.model.Booking;
import com.regnquiz.model.Login;
import com.regnquiz.model.Type;
import com.regnquiz.model.User;
import com.regnquiz.model.repositories.TypeRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.UserTypeQueryRepository;
import com.regnquiz.model.repositories.UserTypeRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(method=RequestMethod.GET, value = "/login")
    public String getLogin(@ModelAttribute("login") Login login, Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid Login login, BindingResult result, ModelMap model, HttpServletRequest request) {
        try {
            int userType = userTypeQueryRepository.getUserTypes(login.getUserID()).getTypeID();
            int userID = userRepository.findById(login.getUserID()).get().getUserID();
            HttpSession session = request.getSession();
            session.setAttribute("userType",userType);
            session.setAttribute("userID",userID);
            System.out.println("Session Login "+ session.getId());
        }catch(InvalidDataAccessResourceUsageException e){
            return new ModelAndView("redirect:/redirectedUrl");
        }

        model.addAttribute("id", login.getUserID());
        return new ModelAndView("redirect:/staff/{id}", model);
        //return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/user").build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleBadInput(HttpMessageNotReadableException ex)
    {
        Throwable cause = ex.getCause();
        System.out.println(cause.toString());
    }

    @PostMapping(path="/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!session.getId().isEmpty()) {
            session.invalidate();
            return 1;
        }else{
            return -1;
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping(path="/getbooking")
    public @ResponseBody List<Booking> getbooking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return BookingRepository.findByUserID(2);
    }

    @GetMapping(path="/student")
    public View gotoStudent(HttpServletRequest request, HttpServletResponse response) {
        return new RedirectView("/student.html", true);
    }

    @GetMapping(path="/staff/{id}")
    public String goToStaffIndex(@PathVariable("id") int id, Model model,HttpServletRequest request) {
        try {
            if (request.getSession() != null && (Integer) request.getSession().getAttribute("userID") == id) {
                model.addAttribute("user", userRepository.findById(id).get());
                System.out.println(model.toString());
                return "staff";
            } else {
                return "redirect:/";
            }
        }catch(NullPointerException e){
            return "redirect:/";
        }
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

    /*@GetMapping(path="/staff/{id}")
    public @ResponseBody String userCheck(HttpServletRequest request) {
        System.out.println("/staff/{id}");
        HttpSession session = request.getSession();
        if(session != null){
            if(session.getAttribute("userType").equals(2)){
                return "staff";
            }else if(session.getAttribute("userType").equals(2)){
                return "student";
            }
        }
        return "inValid";
    }*/
}