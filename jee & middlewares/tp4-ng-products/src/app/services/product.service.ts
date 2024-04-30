import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private host = "http://localhost:1111"

  constructor(private https: HttpClient) { }

  public getProducts(keyword :string="", page: number = 1, limit :number = 2) {
    return this.https.get(`${this.host}/products?name_like=${keyword}&_page=${page}&_limit=${limit}`, {observe: 'response'})
  }

  public toggleCheck(product :Product) :Observable<Product> {
    return this.https.patch<Product>(`${this.host}/products/${product.id}`,
      {checked: !product.checked})
  }

  public delete(productId :number) :Observable<any> {
    return this.https.delete(`${this.host}/products/${productId}`)
  }

  public save(product :Product) :Observable<Product> {
    return this.https.post<Product>(`${this.host}/products/`, product)
  }

  public getProductById(productId :number) {
    return this.https.get<Product>(`${this.host}/products/${productId}`)
  }

  update(product: Product) {
    return this.https.put<Product>(`${this.host}/products/${product.id}`, product)
  }
}
