import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private https: HttpClient) { }

  public getProducts(keyword :string="", page: number = 1, limit :number = 2) {
    return this.https.get(`http://localhost:1111/products?name_like=${keyword}&_page=${page}&_limit=${limit}`, {observe: 'response'})
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
}
