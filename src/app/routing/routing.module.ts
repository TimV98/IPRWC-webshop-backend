import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ProductComponent} from '../products/product.component';
import {LoginComponent} from '../login/login.component';
import {ShoppingCartComponent} from '../shopping-cart/shopping-cart.component';
import {ProductAddComponent} from '../products/product-add/product-add.component';


const appRoutes: Routes = [
  {path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'products', component: ProductComponent},
  {path: 'login', component: LoginComponent},
  {path: 'shoppingcart', component: ShoppingCartComponent},
  {path: 'addProduct', component: ProductAddComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class RoutingModule {
}
