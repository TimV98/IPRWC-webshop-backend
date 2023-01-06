import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {RoutingModule} from './routing/routing.module';
import {ProductComponent} from './products/product.component';
import {ProductItemsComponent} from './products/product_items/product-items.component';
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import { BillingComponent } from './billing/billing.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ShoppingCartListComponent } from './shopping-cart/shopping-cart-list/shopping-cart-list.component';
import { ShoppingCartItemComponent } from './shopping-cart/shopping-cart-item/shopping-cart-item.component';
import { ProductAddComponent } from './products/product-add/product-add.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    ProductComponent,
    ProductItemsComponent,
    LoginComponent,
    BillingComponent,
    ShoppingCartComponent,
    ShoppingCartListComponent,
    ShoppingCartItemComponent,
    ProductAddComponent

  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
