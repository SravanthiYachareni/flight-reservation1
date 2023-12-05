package com.sravs.reservation.controller;

import com.sravs.reservation.entities.User;
import com.sravs.reservation.repos.UserRepository;
import com.sravs.reservation.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;
    @Autowired
    SecurityService securityService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private BCryptPasswordEncoder encoder;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        LOGGER.info("Inside showRegistrationPage()");
        return "/registerUser";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
        return "/login";
    }

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        LOGGER.info("Inside  save()"+user);
        repository.save(user);
        return "/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String
            password, ModelMap modelMap) {
       LOGGER.info("Inside login() and email is " + email);
      boolean loginResponse=securityService.login(email, password);
        if (loginResponse) {
            return "/findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid user name or password.Please try again");
        }
        return "/login";
    }

}
