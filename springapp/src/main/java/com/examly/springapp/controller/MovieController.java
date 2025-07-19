package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Movie;
import com.examly.springapp.service.serviceImpl.MovieServiceImpl;



@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;
    
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @PutMapping("/{movieId}")
    public Movie updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        return movieService.updateMovie(movieId, movie);
    }

    @DeleteMapping("/{movieId}")
    public boolean deleteMovie(@PathVariable Long movieId) {
        return movieService.deleteMovie(movieId);
    }


}
