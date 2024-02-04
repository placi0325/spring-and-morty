import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private UserService: UserService, private router: Router){}

  email: string = '';
  password: string = '';
  errorMessage: string = '';

  submitted = false;

  onSubmit() { 
    console.log(this.email);
    console.log(this.password);

    this.UserService.login(this.email, this.password);
    this.submitted = true; 
    this.router.navigate(["/"]);
  }
  
}
