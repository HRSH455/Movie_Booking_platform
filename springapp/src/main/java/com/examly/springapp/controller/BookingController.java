package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.service.BookingService;

@RestController
public class BookingController {

    @Autowired
    BookingService service;

    @Autowired
    BookingRepo repoB;

    @PostMapping("/api/booking")
    public ResponseEntity<?>addBooking(@RequestBody Booking booking){
        
        try {
            
            return new ResponseEntity<>(service.addBooking(booking),HttpStatus.valueOf(201));
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }

    }

    @GetMapping("/api/booking/{bookingId}")
    public ResponseEntity<?>getBookingById(@PathVariable int bookingId){
        
    }


}
