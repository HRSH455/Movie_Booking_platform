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