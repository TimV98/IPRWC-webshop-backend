import { Component, OnInit } from '@angular/core';
import {Product} from '../../products/Product.model';
import {ShoppingCartService} from '../shopping-cart.service';

@Component({
  selector: 'app-shopping-cart-list',
  templateUrl: './shopping-cart-list.component.html',
  styleUrls: ['./shopping-cart-list.component.css']
})
export class ShoppingCartListComponent implements OnInit {

  cartProducts = [];

  constructor(private service: ShoppingCartService) { }

  ngOnInit() {

    this.service.getAllCartProducts().subscribe((product: Product[]) => {
      this.cartProducts = product});
  }

}
