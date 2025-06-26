package com.examly.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/api/test/welcome")
    public String Welcome(){
        return "Welcome to the Movie Booking Application";
    }

    @GetMapping("api/test/movie")
    public ResponseEntity<?>getAll(){
        List<
    }

}
