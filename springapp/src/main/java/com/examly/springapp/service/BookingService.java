package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.exception.InsufficientSeatCountException;


@Service
public interface BookingService {
    Booking createBooking(Booking booking) throws InsufficientSeatCountException;
    Booking getBookingById(long bookingId);
    List<Booking> getAllBooking();
    boolean deleteBooking(long bookingId);
    List<Booking> getBookingsByMovieId(Long movieId);
    List<Booking> getBookingsByUserId(int userId);
}
