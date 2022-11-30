package com.aloyolaa.springbootschedule.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("config.schedule.open")
    private Integer open;

    @Value("config.schedule.close")
    private Integer close;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Customer Service Hours");
        return "index";
    }

    @GetMapping("/close")
    public String close(Model model) {
        model.addAttribute("title", "Out of Office Hours");
        model.addAttribute("message", "Close. We serve from " + open + " hrs to " + close + " hrs.");
        return "close";
    }

}
