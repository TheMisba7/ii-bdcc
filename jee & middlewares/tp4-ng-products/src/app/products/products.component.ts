import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products! : Array<Product>
  public keyword :string = ""
  constructor(private productService :ProductService) {}
  ngOnInit(): void {
    this.productService.getProducts()
      .subscribe({
        next: data => {this.products = data}
      })
    }

  toggleCheck(product: Product) {
    this.productService.toggleCheck(product)
      .subscribe({
        next: updatedProduct => {
          product.checked = !product.checked
        },
        error: err => {
          console.log(err)
        }
      })
  }

  delete(product: Product) {
    this.productService.delete(product.id)
      .subscribe({
        next: res => {
          this.products = this.products.filter(p => p.id != product.id)
        },
        error: err => {
          console.log(err)
        }
      })
  }

  search() {
    this.productService.search(this.keyword)
      .subscribe({
        next: products => {
          this.products = products
        },
        error: err => {console.error(err)}
      })
  }
}
