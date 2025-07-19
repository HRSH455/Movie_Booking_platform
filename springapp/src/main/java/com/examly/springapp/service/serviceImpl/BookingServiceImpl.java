package com.examly.springapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.entity.Movie;
import com.examly.springapp.exception.InsufficientSeatCountException;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.repository.MovieRepo;

@Service
public class BookingServiceImpl {

   @Autowired
    private BookingRepo bookingRepo;
    
    @Autowired
    private MovieRepo movieRepo;

    @Override
    public Booking createBooking(Booking booking) throws InsufficientSeatCountException {
        Movie movie = booking.getMovie();
        if(movie != null) {
            // Here you could add seat availability validation
            // For now, we'll just save the booking
            return bookingRepo.save(booking);
        }
        throw new InsufficientSeatCountException("Not enough seats available");
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookingRepo.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepo.findAll();
    }

    @Override
    public boolean deleteBooking(long bookingId) {
        if(bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> getBookingsByMovieId(Long movieId) {
        return bookingRepo.findByMovieMovieId(movieId);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepo.findByUserUserId(userId);
    }

}
