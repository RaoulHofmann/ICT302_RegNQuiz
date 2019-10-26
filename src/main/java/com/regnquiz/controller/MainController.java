
package com.regnquiz.controller;

import com.regnquiz.model.*;
import com.regnquiz.model.forms.Login;
import com.regnquiz.model.imports.BookingImport;
import com.regnquiz.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.*;
import java.util.*;


/**
 *
 * @author Raoul Hofmann
 * @comment Main controller for handling login, etc.
 */
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

    @GetMapping(path = "/gettypes")
    public @ResponseBody
    Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
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

        if (result.hasErrors()) {
            model.addAttribute("error", 1);
            return new ModelAndView("/login");
        }

        try{
            userType = userTypeQueryRepository.getUserTypes(login.getUserID(),login.getPassword()).getTypeID();
        }catch (NullPointerException e){
            model.addAttribute("error", 1);
            return new ModelAndView("/login");
        }
        userID = login.getUserID();

        try {
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            session.setAttribute("userID", userID);
            System.out.println("Session Login " + session.getId());
        } catch (NullPointerException e) {
            model.addAttribute("error", 1);
            return new ModelAndView("/login");
        }

        model.addAttribute("id", login.getUserID());

        if (userType == 1) {
            return new ModelAndView("redirect:/admin/{id}", model);
        } else if (userType == 2) {
            return new ModelAndView("redirect:/staff/{id}", model);
        } else if (userType == 3) {
            return new ModelAndView("redirect:/student/{id}", model);
        } else {
            return new ModelAndView("/login");
        }
    }

    @PostMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest request, Model model) {
        try {
            HttpSession session = request.getSession();
            if (!session.getId().isEmpty()) {
                session.invalidate();
            }
        }catch (NullPointerException e){
            model.addAttribute("logout_err", 1);
            return new ModelAndView("/login");
        }

        model.addAttribute("logout", 1);
        return new ModelAndView("/login");
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

    @GetMapping(path = "/admin/{id}")
    public String goToAdminIndex(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            if (request.getSession() != null && (Integer) request.getSession().getAttribute("userID") == id) {
                model.addAttribute("user", userRepository.findById(id).get());
                return "admin";
            } else {
                return "redirect:/";
            }
        } catch (NullPointerException e) {
            return "redirect:/";
        }
    }
    @PostMapping(path = "/admin/{id}/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile fileChooser, @PathVariable("id") int id, Model model, HttpServletRequest request) throws IOException {
        BookingImport bookingImport = new BookingImport();
        bookingImport.ImportBooking(fileChooser);
        model.addAttribute("user", userRepository.findById(id).get());

        return "redirect:/admin/"+id;
    }
}
