import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminaddmovieComponent } from './components/adminaddmovie/adminaddmovie.component';
import { UserviewbookingComponent } from './components/userviewbooking/userviewbooking.component';

const routes: Routes = [
  {path: 'admin/add/newMovies' ,component:AdminaddmovieComponent },
  {path: 'user/view/Mybookings' ,component:UserviewbookingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
