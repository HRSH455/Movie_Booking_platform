package com.examly.springapp.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movieId;
    private String title;
    private int duration;
    private String genre;
    private int price;

    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Movie() {
    }

    public Movie(long movieId, String title, int duration, String genre, int price, List<Booking> bookings) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.price = price;
        this.bookings = bookings;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    

        
}
