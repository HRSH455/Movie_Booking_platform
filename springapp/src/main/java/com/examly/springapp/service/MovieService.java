package com.examly.springapp.service;
import java.util.List;
import com.examly.springapp.entity.Movie;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long movieId);
    Movie updateMovie(Long movieId, Movie movie);
    boolean deleteMovie(Long movieId);
}
