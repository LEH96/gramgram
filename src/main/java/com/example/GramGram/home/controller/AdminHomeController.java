package com.example.GramGram.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public String showMain() {
        return "admin/home/main";
    }
}
