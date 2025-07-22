nav ul {
    list-style: none;
    padding: 0;
    display: flex;
    gap: 1rem;
}
nav ul li a{
    text-decoration: none;
    color: #007bff;
}
nav ul li a:hover{
    text-decoration: underline;
}

<nav>
    <ul>
        <li><a routerLink="/admin/add/newMovies">Add Movies</a></li>
        <li><a routerLink="/admin/view/Movies">View Movies</a></li>
        <li><a routerLink="/admin/view/AllBookings">View Bookings</a></li>
    </ul>
</nav>


import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
