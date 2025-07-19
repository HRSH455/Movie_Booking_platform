package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findByMovieId(Long movieId);
    Optional<Movie> findByMovieId(int userId);
}
