package com.example.NewWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoadingDataController {

    @GetMapping(value = "/loadingData")
    public Model setprice(@RequestParam("id") int id,
                           @RequestParam("pricemin") int pricemin,
                           @RequestParam("pricemax") int pricemax,
                           Model model){
        model.addAttribute("id", id);
        model.addAttribute("pricemin", pricemin);
        model.addAttribute("pricemax", pricemax);

        return model;
    }
}
