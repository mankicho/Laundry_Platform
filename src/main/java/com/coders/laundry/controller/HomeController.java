package com.coders.laundry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<String> coders = List.of("도경윤", "송호찬", "이의인", "조만기", "차성호");
        model.addAttribute("coders", coders);
        return "home";
    }
}
