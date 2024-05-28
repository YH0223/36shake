package com.example.NewWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WebController {
    @GetMapping("/main2")
    public String mainPage() {
        // 메인 페이지로 이동할 뷰의 이름을 반환합니다.
        return "main2";
    }
}
