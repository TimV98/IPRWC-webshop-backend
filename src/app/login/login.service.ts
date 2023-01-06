import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiString: string = "http://localhost:8080/api/users/me";

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
    return this.http.get(this.apiString)
  }
}
