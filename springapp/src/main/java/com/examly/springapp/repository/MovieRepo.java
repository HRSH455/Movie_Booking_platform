package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

  List<Booking> findByMovieMovieId(Long movieId);
    List<Booking> findByUserUserId(int userId);
}
