import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-adminviewbooking',
  templateUrl: './adminviewbooking.component.html',
  styleUrls: ['./adminviewbooking.component.css']
})
export class AdminviewbookingComponent implements OnInit {
  booking =[];
  constructor() { }

  ngOnInit(): void {
  }

}
