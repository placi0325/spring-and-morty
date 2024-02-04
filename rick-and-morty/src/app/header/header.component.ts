import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit{
  constructor(private userService: UserService, private router: Router){}

  showHiddenButtons: boolean = false;
  isUserLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isUserLoggedIn = this.userService.isUserLoggedIn();
  }

  logOut(): void {
    this.userService.logout();
    this.router.navigate(["/"]);
  }

  toggleButtons(){
    this.showHiddenButtons = !this.showHiddenButtons;
  }
}
