package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long>{
    // public findByMovieMovieId(Long movieId);
    // public findByUserUserId(int userId);
}
