import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProductsComponent} from "./products/products.component";
import {NewProductsComponent} from "./new-products/new-products.component";
import { EditProductsComponent } from './edit-products/edit-products.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "products", component: ProductsComponent},
  {path: "new-product", component: NewProductsComponent},
  {path :"editProduct/:productId", component: EditProductsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
