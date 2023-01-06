import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {User} from './User.model';
import {AuthguardService} from '../authguard.service';
import {LoginService} from './login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('loginForm') form: NgForm;
  public user: User = new User();

  constructor(private authGuard: AuthguardService, private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
    sessionStorage.clear();
  }


  onSubmit() {
    const email = this.form.value['email'];
    const password = this.form.value['password'];
    const hashedPassword = btoa(password);

  }
}
