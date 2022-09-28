package com.prajitha.securityexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    //API for user role
    @GetMapping("/user")
    public String getUser(){
        return "At Page;";
    }

}
