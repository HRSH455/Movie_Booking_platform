import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { AdminaddmovieComponent } from './components/adminaddmovie/adminaddmovie.component';
import { AdminviewmovieComponent } from './components/adminviewmovie/adminviewmovie.component';
import { AdminviewbookingComponent } from './components/adminviewbooking/adminviewbooking.component';
import { UserviewbookingComponent } from './components/userviewbooking/userviewbooking.component';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminaddmovieComponent,
    AdminviewmovieComponent,
    AdminviewbookingComponent,
    UserviewbookingComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
