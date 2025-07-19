package com.examly.springapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long>{

    List<Booking> findByMovieMovieId(Long movieId);

    List<Booking> findByUserUserId(int userId);
    
}
