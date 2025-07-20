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