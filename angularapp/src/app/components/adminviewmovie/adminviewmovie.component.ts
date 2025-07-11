import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/models/movie.model';

@Component({
  selector: 'app-adminviewmovie',
  templateUrl: './adminviewmovie.component.html',
  styleUrls: ['./adminviewmovie.component.css']
})
export class AdminviewmovieComponent implements OnInit {

   movies =[];
   errorMessage ='';
   updateMovie(){
    
   }
   deleteMovie(){

   }

  constructor() { }

  ngOnInit(): void {
  }

}
