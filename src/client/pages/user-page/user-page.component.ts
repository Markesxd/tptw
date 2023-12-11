import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from 'src/client/app/services/user.service';
import { IUser, User } from 'src/client/model/User.model';

@Component({
  selector: 'user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css'],
  standalone: true
})
export class UserPageComponent implements OnInit{

  user: IUser = new User;

  constructor (
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    const id = this.cookieService.get('id');
    if(id === '') {
      this.router.navigate(['']);
      return;
    }
    this.userService.fetch(id).subscribe(user => {
      this.user = user;
    });
  }

  logout(): void {
    this.cookieService.delete('id');
    this.router.navigate(['/']);
  }
}
