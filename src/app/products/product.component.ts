import {Component, OnInit} from '@angular/core';
import {ProductService} from './product.service';
import {Product} from './Product.model';

@Component({
  selector: 'app-masks',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  public products = [];
  constructor(private productService: ProductService) {
  }


  ngOnInit() {
    this.productService.getAllProducts().subscribe((product: Product[]) => {
      this.products = product;
      console.log(product);
    });

  }

}



