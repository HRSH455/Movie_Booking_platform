# abbaeeabb329445339bdafdcdedbeeccfone
https://sonarcloud.io/summary/overall?id=iamneo-production_abbaeeabb329445339bdafdcdedbeeccfone






    package com.example.springapp.service;

import com.example.springapp.entity.Booking;
import com.example.springapp.exception.InsufficientSeatCountException;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking) throws InsufficientSeatCountException;
    Booking getBookingById(long bookingId);
    List<Booking> getAllBooking();
    boolean deleteBooking(long bookingId);
    List<Booking> getBookingsByMovieId(Long movieId);
    List<Booking> getBookingsByUserId(int userId);
}