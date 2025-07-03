package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.service.BookingService;
import com.examly.springapp.service.serviceImpl.BookingServiceImpl;

@RestController
public class BookingController {

    @Autowired
    BookingServiceImpl service;

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
        List<Booking> b = service.getBookingById(bookingId);
        if(!b.isEmpty()){
            return new ResponseEntity<>(b,HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(500));

    }

    @GetMapping("/api/booking/movie/{movieId}")
    public ResponseEntity<?>getBookingsBymovieId(@PathVariable int bookingId){
        List<Booking> b = service.getBookingBymovieId(bookingId);
        if(!b.isEmpty()){
            return new ResponseEntity<>(b,HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(500));

    }

    @DeleteMapping("/api/booking/{bookingId}")
    public ResponseEntity<?>deleteBookingById(@PathVariable int bookingId){
        boolean b = service.deleteBookingById(bookingId);
        if(b){
            return new ResponseEntity<>(b,HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(500));

    }






}
