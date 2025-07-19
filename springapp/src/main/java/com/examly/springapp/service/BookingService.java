package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.exception.InsufficientSeatCountException;


public interface BookingService {
    Booking createBooking(Booking booking) 
    Booking getBookingById(long bookingId);
    List<Booking> getAllBooking();
    boolean deleteBooking(long bookingId);
    List<Booking> getBookingsByMovieId(Long movieId);
    List<Booking> getBookingsByUserId(int userId);
}
