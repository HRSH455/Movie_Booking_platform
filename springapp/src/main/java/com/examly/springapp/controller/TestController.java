package com.examly.springapp.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/test/welcome")
    public String welcomeMessage(){
        return "Welcome to the Movie Booking Application";
    }
}
