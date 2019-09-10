
package com.mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
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
    public @ResponseBody Iterable<Type> getUserTypes(@RequestParam int userid) {
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
}