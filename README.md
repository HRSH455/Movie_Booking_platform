# abbaeeabb329445339bdafdcdedbeeccfone
https://sonarcloud.io/summary/overall?id=iamneo-production_abbaeeabb329445339bdafdcdedbeeccfone

  
  
Could not pasteI'll provide you with a complete implementation for both the backend (Spring Boot) and frontend (Angular) parts of this Movie Booking Application. Due to the size of the project, I'll break it down into manageable sections.

## Backend Implementation (Spring Boot)

### 1. Project Structure

```
com.example.springapp
├── config
│   ├── CorsConfig.java
│   └── SecurityConfig.java
├── controller
│   ├── BookingController.java
│   ├── MovieController.java
│   ├── TestController.java
│   └── UserController.java
├── entity
│   ├── Booking.java
│   ├── Movie.java
│   └── User.java
├── exception
│   ├── BookingNotFoundException.java
│   └── InsufficientSeatCountException.java
├── repository
│   ├── BookingRepo.java
│   ├── MovieRepo.java
│   └── UserRepo.java
├── service
│   ├── BookingService.java
│   ├── MovieService.java
│   ├── UserService.java
│   └── serviceImpl
│       ├── BookingServiceImpl.java
│       ├── MovieServiceImpl.java
│       └── UserServiceImpl.java
└── SpringAppApplication.java
```

### 2. Entity Classes

#### Movie.java
```java
package com.example.springapp.entity;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private int duration;
    private String genre;
    private int price;
    
    // Constructors, getters and setters
    public Movie() {}
    
    public Movie(Long movieId, String title, int duration, String genre, int price) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.price = price;
    }

    // Getters and setters
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
```

#### Booking.java
```java
package com.example.springapp.entity;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private int seatCount;
    private double totalCost;
    
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    // Constructors, getters and setters
    public Booking() {}
    
    public Booking(Long bookingId, int seatCount, double totalCost, Movie movie, User user) {
        this.bookingId = bookingId;
        this.seatCount = seatCount;
        this.totalCost = totalCost;
        this.movie = movie;
        this.user = user;
    }

    // Getters and setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
```

#### User.java
```java
package com.example.springapp.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole;
    
    // Constructors, getters and setters
    public User() {}
    
    public User(int userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}
```

### 3. Repository Interfaces

#### MovieRepo.java
```java
package com.example.springapp.repository;

import com.example.springapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
}
```

#### BookingRepo.java
```java
package com.example.springapp.repository;

import com.example.springapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByMovieMovieId(Long movieId);
    List<Booking> findByUserUserId(int userId);
}
```

#### UserRepo.java
```java
package com.example.springapp.repository;

import com.example.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
```

### 4. Service Interfaces

#### MovieService.java
```java
package com.example.springapp.service;

import com.example.springapp.entity.Movie;
import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long movieId);
    Movie updateMovie(Long movieId, Movie movie);
    boolean deleteMovie(Long movieId);
}
```

#### BookingService.java
```java
package com.example.springapp.service;

import com.example.springapp.entity.Booking;
import com.example.springapp.exception.InsufficientSeatCountException;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking) throws InsufficientSeatCountException;
    Booking getBookingById(long bookingId);
    List<Booking> getAllBooking();
    boolean deleteBooking(long bookingId);
    List<Booking> getBookingsByMovieId(Long movieId);
    List<Booking> getBookingsByUserId(int userId);
}
```

#### UserService.java
```java
package com.example.springapp.service;

import com.example.springapp.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUser();
    User loginUser(String email, String password);
}
```

### 5. Service Implementations

#### MovieServiceImpl.java
```java
package com.example.springapp.service.serviceImpl;

import com.example.springapp.entity.Movie;
import com.example.springapp.repository.MovieRepo;
import com.example.springapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
```

#### BookingServiceImpl.java
```java
package com.example.springapp.service.serviceImpl;

import com.example.springapp.entity.Booking;
import com.example.springapp.entity.Movie;
import com.example.springapp.exception.InsufficientSeatCountException;
import com.example.springapp.repository.BookingRepo;
import com.example.springapp.repository.MovieRepo;
import com.example.springapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    
    @Autowired
    private BookingRepo bookingRepo;
    
    @Autowired
    private MovieRepo movieRepo;

    @Override
    public Booking createBooking(Booking booking) throws InsufficientSeatCountException {
        Movie movie = booking.getMovie();
        if(movie != null) {
            // Here you could add seat availability validation
            // For now, we'll just save the booking
            return bookingRepo.save(booking);
        }
        throw new InsufficientSeatCountException("Not enough seats available");
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookingRepo.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepo.findAll();
    }

    @Override
    public boolean deleteBooking(long bookingId) {
        if(bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> getBookingsByMovieId(Long movieId) {
        return bookingRepo.findByMovieMovieId(movieId);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepo.findByUserUserId(userId);
    }
}
```

#### UserServiceImpl.java
```java
package com.example.springapp.service.serviceImpl;

import com.example.springapp.entity.User;
import com.example.springapp.repository.UserRepo;
import com.example.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public User registerUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
```

### 6. Exception Classes

#### InsufficientSeatCountException.java
```java
package com.example.springapp.exception;

public class InsufficientSeatCountException extends Exception {
    public InsufficientSeatCountException(String message) {
        super(message);
    }
}
```

#### BookingNotFoundException.java
```java
package com.example.springapp.exception;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
```

### 7. Controllers

#### TestController.java
```java
package com.example.springapp.controller;

import com.example.springapp.entity.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Movie Booking Application";
    }
    
    @GetMapping("/movie")
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie 1", 120, "Action", 10));
        movies.add(new Movie(2L, "Movie 2", 90, "Comedy", 8));
        return movies;
    }
}
```

#### MovieController.java
```java
package com.example.springapp.controller;

import com.example.springapp.entity.Movie;
import com.example.springapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

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
```

#### BookingController.java
```java
package com.example.springapp.controller;

import com.example.springapp.entity.Booking;
import com.example.springapp.exception.BookingNotFoundException;
import com.example.springapp.exception.InsufficientSeatCountException;
import com.example.springapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) throws InsufficientSeatCountException {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable long bookingId) throws BookingNotFoundException {
        Booking booking = bookingService.getBookingById(bookingId);
        if(booking == null) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
        return booking;
    }

    @GetMapping
    public List<Booking> getAllBooking() {
        return bookingService.getAllBooking();
    }

    @DeleteMapping("/{bookingId}")
    public boolean deleteBooking(@PathVariable long bookingId) throws BookingNotFoundException {
        if(!bookingService.deleteBooking(bookingId)) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
        return true;
    }

    @GetMapping("/movie/{movieId}")
    public List<Booking> getBookingsByMovieId(@PathVariable Long movieId) {
        return bookingService.getBookingsByMovieId(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable int userId) {
        return bookingService.getBookingsByUserId(userId);
    }
}
```

#### UserController.java
```java
package com.example.springapp.controller;

import com.example.springapp.entity.User;
import com.example.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
```

### 8. Configuration Classes

#### SecurityConfig.java
```java
package com.example.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated();
        return http.build();
    }
}
```

#### CorsConfig.java
```java
package com.example.springapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
```

### 9. Main Application Class

#### SpringAppApplication.java
```java
package com.example.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);
    }
}
```

## Frontend Implementation (Angular)

### 1. Project Structure

```
src/app
├── components
│   ├── adminaddmovie
│   ├── adminnav
│   ├── adminviewbooking
│   ├── adminviewmovie
│   ├── authguard
│   ├── error
│   ├── home
│   ├── login
│   ├── navbar
│   ├── registration
│   ├── userbookingmovie
│   ├── usernav
│   ├── userviewbooking
│   └── userviewmovie
├── models
│   ├── booking.model.ts
│   ├── login.model.ts
│   ├── movie.model.ts
│   └── user.model.ts
├── services
│   ├── auth.service.ts
│   ├── booking.service.ts
│   └── movie.service.ts
└── app-routing.module.ts
```

### 2. Models

#### booking.model.ts
```typescript
export interface Booking {
    bookingId: number;
    userId: number;
    movieId: number;
    seatCount?: number;
    totalCost: number;
}
```

#### login.model.ts
```typescript
export class Login {
    email: string;
    password: string;
}
```

#### movie.model.ts
```typescript
export interface Movie {
    movieId?: number;
    title: string;
    genre: string;
    duration: number;
    price: number;
}
```

#### user.model.ts
```typescript
export class User {
    userId?: number;
    email: string;
    password: string;
    username: string;
    mobileNumber: string;
    userRole: string;
}
```

### 3. Services

#### auth.service.ts
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/user';
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User | null>(JSON.parse(localStorage.getItem('currentUser') || 'null'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/register`, user);
  }

  login(login: Login): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/login`, login);
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  isLoggedIn(): boolean {
    return !!this.currentUserValue;
  }

  isAdmin(): boolean {
    return this.currentUserValue?.userRole === 'ADMIN';
  }

  isUser(): boolean {
    return this.currentUserValue?.userRole === 'USER';
  }

  getUsername(): string | null {
    return this.currentUserValue?.username || null;
  }
}
```

#### booking.service.ts
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/booking.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private baseUrl = 'http://localhost:8080/api/booking';

  constructor(private http: HttpClient) { }

  addBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(this.baseUrl, booking);
  }

  getBookingById(bookingId: number): Observable<Booking> {
    return this.http.get<Booking>(`${this.baseUrl}/${bookingId}`);
  }

  getAllBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.baseUrl);
  }

  deleteBooking(bookingId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/${bookingId}`);
  }

  getBookingsByMovieId(movieId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/movie/${movieId}`);
  }

  getBookingsByUserId(userId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.baseUrl}/user/${userId}`);
  }
}
```

#### movie.service.ts
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private baseUrl = 'http://localhost:8080/api/movie';

  constructor(private http: HttpClient) { }

  addMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(this.baseUrl, movie);
  }

  getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.baseUrl);
  }

  getMovieById(movieId: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.baseUrl}/${movieId}`);
  }

  updateMovie(movieId: number, movie: Movie): Observable<Movie> {
    return this.http.put<Movie>(`${this.baseUrl}/${movieId}`, movie);
  }

  deleteMovie(movieId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/${movieId}`);
  }
}
```

### 4. Components

#### home.component.ts
```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title = 'MovieBooking';
}
```

#### home.component.html
```html
<div class="container">
  <h1>BOOK MYSHOW</h1>
  <div class="welcome-section">
    <h2>Welcome to MovieBooking: Your Ultimate Cinema Experience</h2>
    <p>
      At MovieBooking, we strive to provide you with the best cinematic experiences. 
      Whether you're looking for the latest blockbusters, indie films, or family favorites, 
      our platform offers an extensive selection of movies to suit every taste.
    </p>
    <p>
      With our user-friendly interface, you can easily browse movie listings, check showtimes, 
      and book your tickets in just a few clicks. Enjoy exclusive discounts and promotions 
      while experiencing the thrill of the big screen.
    </p>
    <p>
      Join us in making your movie nights unforgettable! Discover new films, read reviews, 
      and plan your next outing with friends and family. MovieBooking is here to enhance your cinema journey.
    </p>
  </div>
</div>
```

#### navbar.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showLogoutPopup = false;
  isLoggedIn = false;
  userRole: string | null = null;
  username: string | null = null;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(user => {
      this.isLoggedIn = !!user;
      this.userRole = user?.userRole || null;
      this.username = user?.username || null;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
```

#### navbar.component.html
```html
<nav>
  <h1>BOOK MYSHOW</h1>
  <ul *ngIf="!isLoggedIn">
    <li><a routerLink="/register">Register</a></li>
    <li><a routerLink="/login">Login</a></li>
  </ul>
  <ul *ngIf="isLoggedIn">
    <li>Welcome {{username}} / {{userRole}}</li>
    <li><a routerLink="/">Home</a></li>
    <ng-container *ngIf="userRole === 'ADMIN'">
      <li><a routerLink="/admin/add-movie">Add Movie</a></li>
      <li><a routerLink="/admin/view-movies">View Movies</a></li>
      <li><a routerLink="/admin/view-bookings">View Bookings</a></li>
    </ng-container>
    <ng-container *ngIf="userRole === 'USER'">
      <li><a routerLink="/user/view-movies">View Movies</a></li>
      <li><a routerLink="/user/view-bookings">My Bookings</a></li>
    </ng-container>
    <li><button (click)="showLogoutPopup = true">Logout</button></li>
  </ul>
  <div class="logout-popup" *ngIf="showLogoutPopup">
    <p>Are you sure you want to logout?</p>
    <button (click)="logout()">Yes, Logout</button>
    <button (click)="showLogoutPopup = false">Cancel</button>
  </div>
</nav>
```

#### registration.component.ts
```typescript
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registerForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]],
      confirmPassword: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      userRole: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value 
      ? null : { mismatch: true };
  }

  register() {
    if (this.registerForm.valid) {
      const user: User = {
        username: this.registerForm.value.username,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password,
        mobileNumber: this.registerForm.value.mobileNumber,
        userRole: this.registerForm.value.userRole
      };

      this.authService.register(user).subscribe(
        response => {
          this.successMessage = 'Registration successful! Please login.';
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error => {
          this.errorMessage = 'Registration failed. Please try again.';
        }
      );
    }
  }
}
```

#### registration.component.html
```html
<div class="registration-container">
  <h2>Registration</h2>
  <form [formGroup]="registerForm" (ngSubmit)="register()">
    <div class="form-group">
      <label for="username">Username*</label>
      <input type="text" id="username" formControlName="username">
      <div class="error-message" *ngIf="registerForm.get('username')?.hasError('required') && registerForm.get('username')?.touched">
        Username is required
      </div>
    </div>

    <div class="form-group">
      <label for="email">Email*</label>
      <input type="email" id="email" formControlName="email">
      <div class="error-message" *ngIf="registerForm.get('email')?.hasError('required') && registerForm.get('email')?.touched">
        Email is required
      </div>
      <div class="error-message" *ngIf="registerForm.get('email')?.hasError('email') && registerForm.get('email')?.touched">
        Please enter a valid email
      </div>
    </div>

    <div class="form-group">
      <label for="password">Password*</label>
      <input type="password" id="password" formControlName="password">
      <div class="error-message" *ngIf="registerForm.get('password')?.hasError('required') && registerForm.get('password')?.touched">
        Password is required
      </div>
      <div class="error-message" *ngIf="registerForm.get('password')?.hasError('pattern') && registerForm.get('password')?.touched">
        Password must contain at least one uppercase letter, one lowercase letter, one number and one special character
      </div>
    </div>

    <div class="form-group">
      <label for="confirmPassword">Confirm Password*</label>
      <input type="password" id="confirmPassword" formControlName="confirmPassword">
      <div class="error-message" *ngIf="registerForm.get('confirmPassword')?.hasError('required') && registerForm.get('confirmPassword')?.touched">
        Confirm Password is required
      </div>
      <div class="error-message" *ngIf="registerForm.hasError('mismatch') && registerForm.get('confirmPassword')?.touched">
        Passwords do not match
      </div>
    </div>

    <div class="form-group">
      <label for="mobileNumber">Mobile Number*</label>
      <input type="tel" id="mobileNumber" formControlName="mobileNumber">
      <div class="error-message" *ngIf="registerForm.get('mobileNumber')?.hasError('required') && registerForm.get('mobileNumber')?.touched">
        Mobile number is required
      </div>
      <div class="error-message" *ngIf="registerForm.get('mobileNumber')?.hasError('pattern') && registerForm.get('mobileNumber')?.touched">
        Mobile number must be 10 digits
      </div>
    </div>

    <div class="form-group">
      <label for="userRole">Role*</label>
      <select id="userRole" formControlName="userRole">
        <option value="">Select a role</option>
        <option value="ADMIN">Admin</option>
        <option value="USER">User</option>
      </select>
      <div class="error-message" *ngIf="registerForm.get('userRole')?.hasError('required') && registerForm.get('userRole')?.touched">
        Role is required
      </div>
    </div>

    <button type="submit" [disabled]="!registerForm.valid">Register</button>
  </form>

  <div class="success-message" *ngIf="successMessage">
    {{successMessage}}
  </div>

  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>
</div>
```

#### login.component.ts
```typescript
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.loginForm.valid) {
      const loginData: Login = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };

      this.authService.login(loginData).subscribe(
        (user) => {
          if (user) {
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.authService.currentUser.next(user);
            
            if (user.userRole === 'ADMIN') {
              this.router.navigate(['/admin/view-movies']);
            } else {
              this.router.navigate(['/user/view-movies']);
            }
          } else {
            this.errorMessage = 'Invalid email or password';
          }
        },
        (error) => {
          this.errorMessage = 'Login failed. Please try again.';
        }
      );
    }
  }
}
```

#### login.component.html
```html
<div class="login-container">
  <h2>Login</h2>
  <form [formGroup]="loginForm" (ngSubmit)="login()">
    <div class="form-group">
      <label for="email">Email*</label>
      <input type="email" id="email" formControlName="email">
      <div class="error-message" *ngIf="loginForm.get('email')?.hasError('required') && loginForm.get('email')?.touched">
        Email is required
      </div>
      <div class="error-message" *ngIf="loginForm.get('email')?.hasError('email') && loginForm.get('email')?.touched">
        Please enter a valid email address
      </div>
    </div>

    <div class="form-group">
      <label for="password">Password*</label>
      <input type="password" id="password" formControlName="password">
      <div class="error-message" *ngIf="loginForm.get('password')?.hasError('required') && loginForm.get('password')?.touched">
        Password is required
      </div>
    </div>

    <button type="submit" [disabled]="!loginForm.valid">Login</button>
  </form>

  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div class="register-link">
    Don't have an account? <a routerLink="/register">Register here</a>
  </div>
</div>
```

#### adminviewmovie.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-adminviewmovie',
  templateUrl: './adminviewmovie.component.html',
  styleUrls: ['./adminviewmovie.component.css']
})
export class AdminviewmovieComponent implements OnInit {
  movies: Movie[] = [];
  errorMessage: string = '';

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.movieService.getAllMovies().subscribe(
      (movies) => {
        this.movies = movies;
      },
      (error) => {
        this.errorMessage = 'Failed to load movies. Please try again later.';
      }
    );
  }

  deleteMovie(movieId: number): void {
    if (confirm('Are you sure you want to delete this movie?')) {
      this.movieService.deleteMovie(movieId).subscribe(
        (success) => {
          if (success) {
            this.loadMovies();
          } else {
            this.errorMessage = 'Failed to delete movie.';
          }
        },
        (error) => {
          this.errorMessage = 'Failed to delete movie. Please try again later.';
        }
      );
    }
  }
}
```

#### adminviewmovie.component.html
```html
<div class="admin-movies-container">
  <h2>Manage Movies</h2>
  
  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div *ngIf="movies.length === 0 && !errorMessage">
    No movies available.
  </div>

  <table *ngIf="movies.length > 0">
    <thead>
      <tr>
        <th>Title</th>
        <th>Genre</th>
        <th>Duration (min)</th>
        <th>Price ($)</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let movie of movies">
        <td>{{movie.title}}</td>
        <td>{{movie.genre}}</td>
        <td>{{movie.duration}}</td>
        <td>{{movie.price}}</td>
        <td>
          <button [routerLink]="['/admin/add-movie', movie.movieId]">Edit</button>
          <button (click)="deleteMovie(movie.movieId!)">Delete</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>
```

#### adminaddmovie.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-adminaddmovie',
  templateUrl: './adminaddmovie.component.html',
  styleUrls: ['./adminaddmovie.component.css']
})
export class AdminaddmovieComponent implements OnInit {
  movieForm: FormGroup;
  isEditing = false;
  movieId: number | null = null;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.movieForm = this.fb.group({
      title: ['', Validators.required],
      genre: ['', Validators.required],
      duration: ['', [Validators.required, Validators.min(1)]],
      price: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditing = true;
      this.movieId = +id;
      this.loadMovie(this.movieId);
    }
  }

  loadMovie(movieId: number): void {
    this.movieService.getMovieById(movieId).subscribe(
      (movie) => {
        this.movieForm.patchValue({
          title: movie.title,
          genre: movie.genre,
          duration: movie.duration,
          price: movie.price
        });
      },
      (error) => {
        this.errorMessage = 'Failed to load movie details.';
      }
    );
  }

  addOrUpdateMovie(): void {
    if (this.movieForm.valid) {
      const movie: Movie = {
        movieId: this.movieId || undefined,
        title: this.movieForm.value.title,
        genre: this.movieForm.value.genre,
        duration: this.movieForm.value.duration,
        price: this.movieForm.value.price
      };

      if (this.isEditing && this.movieId) {
        this.movieService.updateMovie(this.movieId, movie).subscribe(
          (updatedMovie) => {
            this.successMessage = 'Movie updated successfully!';
            setTimeout(() => {
              this.router.navigate(['/admin/view-movies']);
            }, 2000);
          },
          (error) => {
            this.errorMessage = 'Failed to update movie. Please try again.';
          }
        );
      } else {
        this.movieService.addMovie(movie).subscribe(
          (newMovie) => {
            this.successMessage = 'Movie added successfully!';
            setTimeout(() => {
              this.router.navigate(['/admin/view-movies']);
            }, 2000);
          },
          (error) => {
            this.errorMessage = 'Failed to add movie. Please try again.';
          }
        );
      }
    }
  }

  closeModal(): void {
    this.router.navigate(['/admin/view-movies']);
  }
}
```

#### adminaddmovie.component.html
```html
<div class="add-movie-container">
  <h2>{{isEditing ? 'Edit Movie' : 'Add New Movie'}}</h2>
  
  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div class="success-message" *ngIf="successMessage">
    {{successMessage}}
  </div>

  <form [formGroup]="movieForm" (ngSubmit)="addOrUpdateMovie()">
    <div class="form-group">
      <label for="title">Movie Title*</label>
      <input type="text" id="title" formControlName="title">
      <div class="error-message" *ngIf="movieForm.get('title')?.hasError('required') && movieForm.get('title')?.touched">
        Movie title is required
      </div>
    </div>

    <div class="form-group">
      <label for="genre">Genre*</label>
      <input type="text" id="genre" formControlName="genre">
      <div class="error-message" *ngIf="movieForm.get('genre')?.hasError('required') && movieForm.get('genre')?.touched">
        Genre is required
      </div>
    </div>

    <div class="form-group">
      <label for="duration">Duration (minutes)*</label>
      <input type="number" id="duration" formControlName="duration">
      <div class="error-message" *ngIf="movieForm.get('duration')?.hasError('required') && movieForm.get('duration')?.touched">
        Duration is required
      </div>
      <div class="error-message" *ngIf="movieForm.get('duration')?.hasError('min') && movieForm.get('duration')?.touched">
        Duration must be at least 1 minute
      </div>
    </div>

    <div class="form-group">
      <label for="price">Price ($)*</label>
      <input type="number" id="price" formControlName="price" step="0.01">
      <div class="error-message" *ngIf="movieForm.get('price')?.hasError('required') && movieForm.get('price')?.touched">
        Price is required
      </div>
      <div class="error-message" *ngIf="movieForm.get('price')?.hasError('min') && movieForm.get('price')?.touched">
        Price must be at least $0
      </div>
    </div>

    <button type="submit" [disabled]="!movieForm.valid">
      {{isEditing ? 'Update Movie' : 'Add Movie'}}
    </button>
  </form>
</div>
```

#### userviewmovie.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userviewmovie',
  templateUrl: './userviewmovie.component.html',
  styleUrls: ['./userviewmovie.component.css']
})
export class UserviewmovieComponent implements OnInit {
  movies: Movie[] = [];
  errorMessage: string = '';

  constructor(
    private movieService: MovieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.movieService.getAllMovies().subscribe(
      (movies) => {
        this.movies = movies;
      },
      (error) => {
        this.errorMessage = 'Failed to load movies. Please try again later.';
      }
    );
  }

  navigateToBooking(movieId: number): void {
    this.router.navigate(['/user/book-movie', movieId]);
  }
}
```

#### userviewmovie.component.html
```html
<div class="user-movies-container">
  <h2>Available Movies</h2>
  
  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div *ngIf="movies.length === 0 && !errorMessage">
    No movies available.
  </div>

  <table *ngIf="movies.length > 0">
    <thead>
      <tr>
        <th>Title</th>
        <th>Genre</th>
        <th>Duration (min)</th>
        <th>Price ($)</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let movie of movies">
        <td>{{movie.title}}</td>
        <td>{{movie.genre}}</td>
        <td>{{movie.duration}}</td>
        <td>{{movie.price}}</td>
        <td>
          <button (click)="navigateToBooking(movie.movieId!)">Book Now</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>
```

#### userbookingmovie.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { MovieService } from '../../services/movie.service';
import { AuthService } from '../../services/auth.service';
import { Booking } from '../../models/booking.model';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-userbookingmovie',
  templateUrl: './userbookingmovie.component.html',
  styleUrls: ['./userbookingmovie.component.css']
})
export class UserbookingmovieComponent implements OnInit {
  bookingForm: FormGroup;
  movie: Movie | null = null;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private bookingService: BookingService,
    private movieService: MovieService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.bookingForm = this.fb.group({
      seatCount: ['', [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit(): void {
    const movieId = this.route.snapshot.paramMap.get('id');
    if (movieId) {
      this.movieService.getMovieById(+movieId).subscribe(
        (movie) => {
          this.movie = movie;
        },
        (error) => {
          this.errorMessage = 'Failed to load movie details.';
        }
      );
    }
  }

  bookMovie(): void {
    if (this.bookingForm.valid && this.movie && this.authService.currentUserValue) {
      const booking: Booking = {
        userId: this.authService.currentUserValue.userId!,
        movieId: this.movie.movieId!,
        seatCount: this.bookingForm.value.seatCount,
        totalCost: this.bookingForm.value.seatCount * this.movie.price
      };

      this.bookingService.addBooking(booking).subscribe(
        (newBooking) => {
          this.successMessage = 'Booking successful!';
          setTimeout(() => {
            this.router.navigate(['/user/view-bookings']);
          }, 2000);
        },
        (error) => {
          this.errorMessage = 'Booking failed. Please try again.';
        }
      );
    }
  }
}
```

#### userbookingmovie.component.html
```html
<div class="booking-container" *ngIf="movie">
  <h2>Book Movie: {{movie.title}}</h2>
  
  <div class="movie-details">
    <p><strong>Genre:</strong> {{movie.genre}}</p>
    <p><strong>Duration:</strong> {{movie.duration}} minutes</p>
    <p><strong>Price per ticket:</strong> ${{movie.price}}</p>
  </div>

  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div class="success-message" *ngIf="successMessage">
    {{successMessage}}
  </div>

  <form [formGroup]="bookingForm" (ngSubmit)="bookMovie()">
    <div class="form-group">
      <label for="seatCount">Number of Seats*</label>
      <input type="number" id="seatCount" formControlName="seatCount">
      <div class="error-message" *ngIf="bookingForm.get('seatCount')?.hasError('required') && bookingForm.get('seatCount')?.touched">
        Number of seats is required
      </div>
      <div class="error-message" *ngIf="bookingForm.get('seatCount')?.hasError('min') && bookingForm.get('seatCount')?.touched">
        You must book at least 1 seat
      </div>
    </div>

    <div class="total-cost" *ngIf="bookingForm.get('seatCount')?.value">
      <p><strong>Total Cost:</strong> ${{bookingForm.get('seatCount')?.value * movie.price}}</p>
    </div>

    <button type="submit" [disabled]="!bookingForm.valid">Confirm Booking</button>
  </form>
</div>
```

#### userviewbooking.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { AuthService } from '../../services/auth.service';
import { Booking } from '../../models/booking.model';

@Component({
  selector: 'app-userviewbooking',
  templateUrl: './userviewbooking.component.html',
  styleUrls: ['./userviewbooking.component.css']
})
export class UserviewbookingComponent implements OnInit {
  bookings: Booking[] = [];
  userId: number | null = null;
  errorMessage: string = '';

  constructor(
    private bookingService: BookingService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const currentUser = this.authService.currentUserValue;
    if (currentUser) {
      this.userId = currentUser.userId || null;
      this.loadUserBookings();
    }
  }

  loadUserBookings(): void {
    if (this.userId) {
      this.bookingService.getBookingsByUserId(this.userId).subscribe(
        (bookings) => {
          this.bookings = bookings;
        },
        (error) => {
          this.errorMessage = 'Failed to load bookings. Please try again later.';
        }
      );
    }
  }
}
```

#### userviewbooking.component.html
```html
<div class="user-bookings-container">
  <h2>My Bookings</h2>
  
  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div *ngIf="bookings.length === 0 && !errorMessage">
    No bookings found.
  </div>

  <table *ngIf="bookings.length > 0">
    <thead>
      <tr>
        <th>Movie Title</th>
        <th>Genre</th>
        <th>Duration (min)</th>
        <th>Seats Booked</th>
        <th>Total Cost ($)</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let booking of bookings">
        <td>{{booking.movie?.title}}</td>
        <td>{{booking.movie?.genre}}</td>
        <td>{{booking.movie?.duration}}</td>
        <td>{{booking.seatCount}}</td>
        <td>{{booking.totalCost}}</td>
        <td>
          <button (click)="cancelBooking(booking.bookingId)">Cancel Ticket</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>
```

#### adminviewbooking.component.ts
```typescript
import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { Booking } from '../../models/booking.model';

@Component({
  selector: 'app-adminviewbooking',
  templateUrl: './adminviewbooking.component.html',
  styleUrls: ['./adminviewbooking.component.css']
})
export class AdminviewbookingComponent implements OnInit {
  bookings: Booking[] = [];
  errorMessage: string = '';

  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.loadBookings();
  }

  loadBookings(): void {
    this.bookingService.getAllBookings().subscribe(
      (bookings) => {
        this.bookings = bookings;
      },
      (error) => {
        this.errorMessage = 'Failed to load bookings. Please try again later.';
      }
    );
  }

  updateBookingStatus(bookingId: number, status: string): void {
    // Implement booking status update logic
    console.log(`Updating booking ${bookingId} to status ${status}`);
  }
}
```

#### adminviewbooking.component.html
```html
<div class="admin-bookings-container">
  <h2>All Bookings</h2>
  
  <div class="error-message" *ngIf="errorMessage">
    {{errorMessage}}
  </div>

  <div *ngIf="bookings.length === 0 && !errorMessage">
    No bookings found.
  </div>

  <table *ngIf="bookings.length > 0">
    <thead>
      <tr>
        <th>Username</th>
        <th>Movie Title</th>
        <th>Genre</th>
        <th>Duration (min)</th>
        <th>Seats Booked</th>
        <th>Total Cost ($)</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr *ngFor="let booking of bookings">
        <td>{{booking.user?.username}}</td>
        <td>{{booking.movie?.title}}</td>
        <td>{{booking.movie?.genre}}</td>
        <td>{{booking.movie?.duration}}</td>
        <td>{{booking.seatCount}}</td>