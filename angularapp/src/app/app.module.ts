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
import { AuthguardComponent } from './components/authguard/authguard.component';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserbookingmovieComponent } from './components/userbookingmovie/userbookingmovie.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewmovieComponent } from './components/userviewmovie/userviewmovie.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminaddmovieComponent,
    AdminviewmovieComponent,
    AdminviewbookingComponent,
    UserviewbookingComponent,
    NavbarComponent,
    AuthguardComponent,
    ErrorComponent,
    LoginComponent,
    RegistrationComponent,
    UserbookingmovieComponent,
    UsernavComponent,
    UserviewmovieComponent,
    AdminnavComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
