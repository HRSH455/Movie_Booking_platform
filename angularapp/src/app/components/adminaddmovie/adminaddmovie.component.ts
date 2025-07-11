import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/models/movie.model';

@Component({
  selector: 'app-adminaddmovie',
  templateUrl: './adminaddmovie.component.html',
  styleUrls: ['./adminaddmovie.component.css']
})
export class AdminaddmovieComponent implements OnInit {
  movie :Movie ={
    title: '',
    genre: '',
    duration: 0,
    price: 0
  };
  isEditing :boolean = true;
  errorMessage ='';
  loadMovie(movieId :number){

  }
  addOrUpdateMovie(){

  }
  closeModal(){
    
  }

  constructor() { }

  ngOnInit(): void {
    if(this.isEditing ===true){

    }
  }

}
