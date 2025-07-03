package com.examly.springapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.repository.BookingRepo;

@Service
public class BookingServiceImpl {

    @Autowired
    BookingRepo repoB;
    public Booking createBooking(Booking booking){
        return repoB.save(booking);
    }
    public  Booking getBookingById(long bookingId){
        
        Booking b =repoB.findById(bookingId).orElse(null);
        return repoB.save(b);

    }
    public List<Booking> getAllBookings(){
        return repoB.findAll();

    }
    public boolean deleteBooking( long bookingId){
        if(repoB.existsById(bookingId)){
            repoB.deleteById(bookingId);
            return true;
        }
        return false;

    }
    public Object addBooking(Booking booking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBooking'");
    }
    public List<Booking> getBookingBymovieId(int bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingBymovieId'");
    }
    public boolean deleteBookingById(int bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBookingById'");
    }

}
