package com.swj9707.twittercloneadmin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainMenu(){
        return "/main";
    }
}
