package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.exception.InsufficientSeatCountException;

public interface BookingService {
    public Booking createBooking(Booking booking) throws InsufficientSeatCountException;
    public List<Booking> getBookingById(long bookingId);
    public List<Booking> getAllBookings();
    public boolean deleteBooking( long bookingId);
    public Object addBooking(Booking booking);
    public boolean deleteBookingById(int bookingId);
    
}
