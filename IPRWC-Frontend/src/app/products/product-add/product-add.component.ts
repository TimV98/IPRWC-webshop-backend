import {Component, OnInit, ViewChild} from '@angular/core';
import {Product} from '../Product.model';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {
  @ViewChild('f') productForm: NgForm;
  constructor() { }

  public product: Product;
  ngOnInit() {
  }

  submitProduct(form:NgForm) {
    this.product.name = this.productForm.value.name;
    this.product.size = this.productForm.value.size;
    this.product.brand = this.productForm.value.brand;
    this.product.price = this.productForm.value.price;
    this.product.imageURL = this.productForm.value.imageURL;

  }
}
