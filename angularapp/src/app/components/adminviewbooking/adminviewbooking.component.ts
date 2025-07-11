import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-adminviewbooking',
  templateUrl: './adminviewbooking.component.html',
  styleUrls: ['./adminviewbooking.component.css']
})
export class AdminviewbookingComponent implements OnInit {
  bookings =[];
  loadBookings(){
    
  }
  updateBookingStatus(bookingId:number ,status :string){

  }
  constructor() { }

  ngOnInit(): void {
  }

}
