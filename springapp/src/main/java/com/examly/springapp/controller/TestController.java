package com.examly.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Movie;

@RestController
public class TestController {


    @GetMapping("/api/test/welcome")
    public String Welcome(){
        return "Welcome to the Movie Booking Application";
    }

    @GetMapping("api/test/movie")
    public List<Movie> getMovies(){
        List<Movie> li = new ArrayList<>();
        
        li.add(new Movie(1l,"Don",120,"thriller",200));
        li.add(new Movie(2l,"Donreturns",193,"RomCom",210));
        li.add(new Movie(3l,"Donret",165,"Crime",120));
        li.add(new Movie(4l,"Don2",124,"Comedy",150));
        li.add(new Movie(5l,"Don4",150,"Dramedy",204));
        return li;
    }

}
