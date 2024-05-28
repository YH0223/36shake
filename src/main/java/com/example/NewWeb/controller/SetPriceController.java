package com.example.NewWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SetPriceController {

    @GetMapping(value = "/setprice")
    public Model setprice(@RequestParam("igid") int igid, Model model) {
        model.addAttribute("igid", igid);
        return model;
    }

}
