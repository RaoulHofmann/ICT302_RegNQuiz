
package com.regnquiz.controller;

import com.regnquiz.model.Type;
import com.regnquiz.model.User;
import com.regnquiz.model.repositories.TypeRepository;
import com.regnquiz.model.repositories.UserRepository;
import com.regnquiz.model.repositories.UserTypeQueryRepository;
import com.regnquiz.model.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping(path="/login")
    public @ResponseBody List<Object> login(@RequestParam int userid, @RequestParam int password, HttpServletRequest request) {
        List<Object> user_info = new ArrayList<>();
        try {
            int userType = userTypeQueryRepository.getUserTypes(userid).getTypeID();
            int userID = userRepository.findById(userid).get().getUserID();
            //if (userRepository.countByUserIDAndUserType_Password(userid, password) == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionIdNo",session.getId());
                session.setAttribute("userType",userType);
                session.setAttribute("userID",userID);
                user_info.add(userType);
                user_info.add(userID);
                user_info.add(userRepository.findById(userid).get().getGivenName());
                user_info.add(userRepository.findById(userid).get().getLastName());
                user_info.add(userRepository.findById(userid).get().getPrefName());
                System.out.println("Session Login "+ session.getId());

            //}
        }catch(InvalidDataAccessResourceUsageException e){
            user_info.add(-2);
        }
        return user_info;
    }

    @PostMapping(path="/logout")
    public @ResponseBody int logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(!session.getId().isEmpty()){
            session.invalidate();
            return 1;
        }else{
            return -1;
        }
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