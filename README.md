import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthUser } from '../models/auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserStoreService {
  private userSubject = new BehaviorSubject<AuthUser | null>(null);
  
  user$: Observable<AuthUser | null> = this.userSubject.asObservable();

  constructor() {
    const storedUser = localStorage.getItem('authUser');
    if (storedUser) {
      this.setUser(JSON.parse(storedUser));
    }
  }

  setUser(user: AuthUser | null): void {
    if (user) {
      localStorage.setItem('authUser', JSON.stringify(user));
    } else {
      localStorage.removeItem('authUser');
    }
    
    this.userSubject.next(user);
  }

  get authUser(): AuthUser | null {
    return this.userSubject.getValue();
  }

  isLoggedIn(): boolean {
    return !!this.authUser;
  }
}

---------------------------------------------

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { UserStoreService } from '../helpers/user-store.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authservice: AuthService,
    private router: Router,private userStore:UserStoreService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(!this.userStore.isLoggedIn()){
        this.router.navigate(['/login']);
        return false;
      }
      const requiredRole=route.data['role'];
      if(requiredRole){
        const userRole=this.userStore.authUser?.role;
        if(requiredRole === 'ADMIN' && userRole !== 'ADMIN'){
          this.router.navigate(['/error']);
          return false;

        }
        if(requiredRole === 'USER' && userRole !== 'USER'){
          this.router.navigate(['/error']);
          return false;
        }

      }
      return true;
  }


}

------------------------------------

adminaddmovie

css
.container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f4f4f4;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  
  .movie-form-container {
    background-color: white;
    padding: 30px;
    border-radius: 8px;
  }
  
  h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
    font-weight: bold;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 5px;
    color: #555;
    font-weight: 600;
  }
  
  .form-control {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    transition: border-color 0.3s ease;
  }
  
  .form-control:focus {
    outline: none;
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
  }
  
  .error-message {
    color: red;
    margin-bottom: 15px;
    text-align: center;
  }
  
  .form-actions {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
  
  .btn {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
  }
  
  .btn-primary {
    background-color: #007bff;
    color: white;
  }
  
  .btn-primary:hover {
    background-color: #0056b3;
  }
  
  .btn-primary:disabled {
    background-color: #6c757d;
    cursor: not-allowed;
  }
  
  .btn-secondary {
    background-color: #6c757d;
    color: white;
  }
  
  .btn-secondary:hover {
    background-color: #545b62;
  }

  html

  <div class="container">
    <div class="movie-form-container">
      <h2>{{ isEditing ? 'Edit Movie' : 'Add New Movie' }}</h2>
      
      <form (ngSubmit)="addOrUpdateMovie()" #movieForm="ngForm">
        <div class="form-group">
          <label for="title">Movie Title</label>
          <input 
            type="text" 
            id="title" 
            name="title" 
            [(ngModel)]="movie.title" 
            required 
            class="form-control"
          >
        </div>
        
        <div class="form-group">
          <label for="duration">Duration (minutes)</label>
          <input 
            type="number" 
            id="duration" 
            name="duration" 
            [(ngModel)]="movie.duration" 
            required 
            class="form-control"
          >
        </div>
        
        <div class="form-group">
          <label for="genre">Genre</label>
          <input 
            type="text" 
            id="genre" 
            name="genre" 
            [(ngModel)]="movie.genre" 
            required 
            class="form-control"
          >
        </div>
        
        <div class="form-group">
          <label for="price">Ticket Price</label>
          <input 
            type="number" 
            id="price" 
            name="price" 
            [(ngModel)]="movie.price" 
            required 
            class="form-control"
          >
        </div>
        
        <div class="form-group">
          <label for="totalSeats">Total Seats</label>
          <input 
            type="number" 
            id="totalSeats" 
            name="totalSeats" 
            [(ngModel)]="movie.totalSeats" 
            required 
            class="form-control"
          >
        </div>
        
        <div *ngIf="errorMessage" class="error-message">
          {{ errorMessage }}
        </div>
        
        <div class="form-actions">
          <button 
            type="submit" 
            [disabled]="!movieForm.form.valid" 
            class="btn btn-primary"
          >
            {{ isEditing ? 'Update Movie' : 'Add Movie' }}
          </button>
          <button 
            type="button" 
            class="btn btn-secondary" 
            (click)="cancelOperation()"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  </div>

--------------------

ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/app/models/movie.model';
import { MovieService } from 'src/app/services/movie.service';
@Component({
  selector: 'app-adminaddmovie',
  templateUrl: './adminaddmovie.component.html',
  styleUrls: ['./adminaddmovie.component.css']
})
export class AdminaddmovieComponent implements OnInit {
  movie: Movie = { id: 0, title: '', duration: 0, genre: '', price: 0, totalSeats: 0 };
  isEditing: boolean = false;
  errorMessage: string = '';
  movieId: number;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private movieService: MovieService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.movieId = params['id'];
    })
    if (this.movieId) {
      console.log(this.movieId);
      this.isEditing = true;
      this.loadMovie(this.movieId);
    }
  }
  loadMovie(id: number): void {
    this.movieService.getMovieById(id).subscribe(
      (data) => (this.movie = data),
      (error) => (this.errorMessage = 'Error loading movie')
    );
  }
  addOrUpdateMovie(): void {
    if (this.isEditing) {
      this.movieService.updateMovie(this.movie.id, this.movie).subscribe(
        () => this.closeModal(),
        (error) => (this.errorMessage = 'Error updating movie')
      );
    }
    else {
      this.movieService.addMovie(this.movie).subscribe(
        () => this.closeModal(),
        (error) => (this.errorMessage = 'Error adding movie')

      )

    }

  }

  closeModal(): void {
    alert(this.isEditing ? "Movie updated Successfullu" : "Movie Added Successfully");
    this.router.navigate(['admin/view/Movies'])
  }
  cancelOperation(): void {
    this.router.navigate(['admin/view/Movies'])

  }



}





import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InvestmentService } from '../services/investment.service';
import { Investment } from '../models/investment.model';

@Component({
  selector: 'app-admin-add-investment',
  templateUrl: './admin-add-investment.component.html',
  styleUrls: ['./admin-add-investment.component.css']
})
export class AdminAddInvestmentComponent implements OnInit {
  investmentForm!: FormGroup;
  isSubmitting = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private investmentService: InvestmentService
  ) {}

  ngOnInit(): void {
    this.investmentForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      purchasePrice: [null, [Validators.required, Validators.min(0)]],
      currentPrice: [null, [Validators.required, Validators.min(0)]],
      quantity: [null, [Validators.required, Validators.min(1)]],
      purchaseDate: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.investmentForm.invalid) {
      this.investmentForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    const investment: Investment = this.investmentForm.value;

    this.investmentService.addInvestment(investment).subscribe({
      next: (response) => {
        alert('Investment added successfully!');
        this.investmentForm.reset();
        this.isSubmitting = false;
      },
      error: (error) => {
        this.errorMessage = error.error?.message || 'Failed to add investment.';
        this.isSubmitting = false;
      }
    });
  }
}

