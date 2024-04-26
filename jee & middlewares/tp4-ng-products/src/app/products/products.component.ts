import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  public products!: Array<Product>
  public keyword: string = ""
  public limit: number = 2
  public currentPage: number = 1
  public totalPages!: number

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.getProducts()
  }

  getProducts() {
    this.productService.getProducts(this.keyword, this.currentPage, this.limit)
      .subscribe({
        next: response => {
          this.products = response.body as Product[]
          let totalProducts: number = parseInt(response.headers.get("X-Total-Count")!)
          this.totalPages = Math.floor(totalProducts / this.limit)
          if (totalProducts % this.limit != 0) {
            this.totalPages++;
          }
          console.log(totalProducts)
          console.log(this.totalPages)
        }
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
    this.getProducts()
  }

  getNextPage(number: number) {
    this.currentPage = number
    this.getProducts()
  }
}
