import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Product} from './products/Product.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiString: string = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }


  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiString);
  }
}
