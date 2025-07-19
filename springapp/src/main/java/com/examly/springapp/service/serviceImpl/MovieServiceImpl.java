package com.examly.springapp.service.serviceImpl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Movie;
import com.examly.springapp.repository.MovieRepo;
import com.examly.springapp.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieRepo.findById(movieId).orElse(null);
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movie) {
        Movie existingMovie = movieRepo.findById(movieId).orElse(null);
        if(existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setDuration(movie.getDuration());
            existingMovie.setGenre(movie.getGenre());
            existingMovie.setPrice(movie.getPrice());
            return movieRepo.save(existingMovie);
        }
        return null;
    }

    @Override
    public boolean deleteMovie(Long movieId) {
        if(movieRepo.existsById(movieId)) {
            movieRepo.deleteById(movieId);
            return true;
        }
        return false;
    }

}
