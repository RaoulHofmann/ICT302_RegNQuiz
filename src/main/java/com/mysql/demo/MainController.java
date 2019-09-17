
package com.mysql.demo;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        n.setName(name);
        n.setEmail(email);
        System.out.println("Email: "+n.getEmail()+" \nName: "+n.getName());

        try {
            userRepository.save(n);
        }catch(DataIntegrityViolationException ex){
            return "Data Integrity Violation Exception"+ ex;
            //throw new DataIntegrityViolationException("Data Integrity Violation Exception");
        }
        return "Saved";
    }

    @PostMapping(path="/login")
    public @ResponseBody List<Integer> login(@RequestParam int userid, @RequestParam int password) {
        List<Integer> user_info = new ArrayList<>();
        try {
            if (userRepository.countByUserIDAndUserType_Password(userid, password) == 1) {
                user_info.add(userTypeQueryRepository.getUserTypes(userid).getTypeID());
                user_info.add(userRepository.findById(userid).get().getUserID());
            }
        }catch(InvalidDataAccessResourceUsageException e){
            user_info.add(-2);
        }
        return user_info;
    }

    @GetMapping(path="/student")
    public View gotoStudent(HttpServletRequest request, HttpServletResponse response) {
        return new RedirectView("/student.html", true);
    }

    @GetMapping(path="/staff")
    public View gotoStaff(HttpServletRequest request, HttpServletResponse response) {
        return new RedirectView("/staff.html", true);
    }

}