import {EventEmitter, Injectable} from '@angular/core';
import {User} from './login/User.model';
import {retry} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService {
  private userURL = 'http://localhost:8080/api/users/me';
  private permanent: boolean;
  adminEmitter = new EventEmitter<boolean>();


  constructor(private http: HttpClient, private router: Router) {
  }

  public getToken() {
    return localStorage.getItem('authToken');
  }

  private setToken(user: User, permanent: boolean = false) {
    this.permanent = permanent;
    return localStorage.setItem('authToken', btoa(`${user.gebruikersnaam}:${user.wachtwoord}`));
  }

  public delToken() {
    return localStorage.removeItem('authToken');
  }

  public isAuthenticated() {
    return !(this.getToken() === null);
  }

  public loginUser(user: User) {
    this.setToken(user, false);
    this.http.get<User>(this.userURL).pipe(retry(4)).subscribe(result => {
      if (user.gebruikersnaam === result.gebruikersnaam) {
        this.setToken(user, true);
        this.router.navigate(['products']);
      }
    });
  }

  public logout() {
    this.delToken();
  }

  emitAdmin() {
    this.adminEmitter.emit(sessionStorage['role'] === 'ADMIN');
  }
}
