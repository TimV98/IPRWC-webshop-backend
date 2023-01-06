import {Injectable} from '@angular/core';
import {Product} from './Product.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  constructor(private http: HttpClient) {
  }

  private apiString: string = "http://localhost:8080/api/wetsuits/";



  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiString)
  }
}
