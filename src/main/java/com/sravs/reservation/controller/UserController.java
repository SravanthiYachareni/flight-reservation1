package com.sravs.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }
}
