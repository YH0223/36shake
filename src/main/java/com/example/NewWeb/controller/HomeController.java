package com.example.NewWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "main2"; // main2.html로 리다이렉트
    }

    @GetMapping("/index")
    String main(){return "main2";}
}