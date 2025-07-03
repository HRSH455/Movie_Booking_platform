package com.examly.springapp.service.serviceImpl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Movie;
import com.examly.springapp.repository.MovieRepo;
import com.examly.springapp.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo repo;
    public Movie add(Movie movie) {
        return repo.save(movie);
       }

    public Movie updateById(long movieId, Movie m) {
            Optional<Movie> o =repo.findById(movieId);
            if(!o.isEmpty()){
                Movie m1 =o.get();
                m1 = m;
                return m;
            }
            return null;
        }

    public List<Movie> getMovie() {
        
      return repo.findAll();
    }

    public Movie getMovieById(long movieId) {
        
         return repo.findById(movieId).orElse(null);
         
        }

    public boolean DeleteById(long movieId) {

    }

}
