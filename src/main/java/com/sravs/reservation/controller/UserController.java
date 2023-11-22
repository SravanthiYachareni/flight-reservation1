package com.sravs.reservation.controller;

import com.sravs.reservation.entities.User;
import com.sravs.reservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "/registerUser";
    }
    @RequestMapping("/showLogin")
    public String showLoginPage() {
        return "/login";
    }

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user) {
        repository.save(user);
        return "/login";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password")String
            password, ModelMap modelMap) {
        User user = repository.findByEmail(email);
        if(user.getPassword().equals(password)){
            return "/findFlights";
        } else {
            modelMap.addAttribute("msg","Invalid user name or password.Please try again");
        }return "/login";
    }

}
