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
    @GetMapping("/api/movie")
    public List<Movie> getMovie(){
        List<Movie> li = movieService.getMovie();
        if(!li.isEmpty()){
            return li;
        }
        return null;
    }

    @GetMapping("/api/movie/{movieId}")
    public List<Movie> getMovie(@PathVariable int movieId){
        List<Movie> li = movieService.getMovieById(movieId);
        if(!li.isEmpty()){
            return li;
        }
        return li;
    }
    @DeleteMapping("/api/movie/{movieId}")
    public boolean DeleteById(@PathVariable int movieId){
        boolean res = movieService.DeleteById(movieId);
        if(res){
            return true;
        }
        return false;
    }


}
