import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private https: HttpClient) { }

  public getProducts() :Observable<Array<Product>> {
    return this.https.get<Array<Product>>("http://localhost:1111/products")
  }

  public toggleCheck(product :Product) :Observable<Product> {
    return this.https.patch<Product>(`http://localhost:1111/products/${product.id}`,
      {checked: !product.checked})
  }

  public delete(productId :number) :Observable<any> {
    return this.https.delete(`http://localhost:1111/products/${productId}`)
  }

  public save(product :Product) :Observable<Product> {
    return this.https.post<Product>("http://localhost:1111/products/", product)
  }

  public search(keyword :string) :Observable<Array<Product>> {
    return this.https.get<Array<Product>>(`http://localhost:1111/products?name_like=${keyword}`)
  }
}
