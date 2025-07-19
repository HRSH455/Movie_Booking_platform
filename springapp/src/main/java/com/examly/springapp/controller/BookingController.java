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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.exception.BookingNotFoundException;
import com.examly.springapp.exception.InsufficientSeatCountException;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.service.BookingService;
import com.examly.springapp.service.serviceImpl.BookingServiceImpl;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) throws InsufficientSeatCountException {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable long bookingId) throws BookingNotFoundException {
        Booking booking = bookingService.getBookingById(bookingId);
        if(booking == null) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
        return booking;
    }

    @GetMapping
    public List<Booking> getAllBooking() {
        return bookingService.getAllBooking();
    }

    @DeleteMapping("/{bookingId}")
    public boolean deleteBooking(@PathVariable long bookingId) throws BookingNotFoundException {
        if(!bookingService.deleteBooking(bookingId)) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
        return true;
    }

    @GetMapping("/movie/{movieId}")
    public List<Booking> getBookingsByMovieId(@PathVariable Long movieId) {
        return bookingService.getBookingsByMovieId(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable int userId) {
        return bookingService.getBookingsByUserId(userId);
    }
}





