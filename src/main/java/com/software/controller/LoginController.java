package com.software.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("accessDenied");
    }
    @GetMapping("/loginFailed")
    public ModelAndView loginFailed() {
        return new ModelAndView("loginFailed");
    }
}