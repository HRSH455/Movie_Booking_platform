package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Movie;
import com.examly.springapp.service.serviceImpl.MovieServiceImpl;



@RestController
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;
    
    @PostMapping("api/movie")
    public ResponseEntity<?>addMovie(@RequestBody Movie movie)
    {
        Movie m = movieService.add(movie);
        return new ResponseEntity<>(m,HttpStatus.valueOf(200));
        
    }

    @PutMapping("api/movie/{movieId}")
    public Movie updateById(@PathVariable long movieId , @RequestBody Movie m)
    {
        return movieService.updateById(movieId,m);
    }
    @GetMapping("")

}
