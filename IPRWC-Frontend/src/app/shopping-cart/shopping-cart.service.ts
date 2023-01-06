import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../products/Product.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  private apiString: string = "http://localhost:8080/api/cart";
  constructor(private http: HttpClient) { }

  public getAllCartProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiString)
  }
}
