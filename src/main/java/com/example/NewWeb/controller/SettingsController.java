package com.example.NewWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String home() {
        return "settings"; // main2.html로 리다이렉트
    }
}