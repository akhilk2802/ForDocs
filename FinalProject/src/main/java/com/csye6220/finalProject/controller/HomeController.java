package com.csye6220.finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String Home(){
        return "redirect:/api/post/all";
    }
}
