package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Booking;

@RestController
public class BookingController {

    @Autowired
    

    @PostMapping("/api/booking")
    public ResponseEntity<?>addBooking(@RequestBody Booking booking){
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
