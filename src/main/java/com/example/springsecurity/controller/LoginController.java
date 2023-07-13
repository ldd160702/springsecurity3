package com.example.springsecurity.controller;

import com.example.springsecurity.temp.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping("/login-page")
    public ModelAndView loginPage() {
        //return new ModelAndView("login-page");
        return new ModelAndView("fancy-login");
    }
}
