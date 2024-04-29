import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Router} from "@angular/router";
import {AppStateService} from "../services/app-state.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {


  constructor(private productService: ProductService,
              private router :Router, public appState : AppStateService) {
  }

  ngOnInit(): void {
    this.getProducts()
  }

  getProducts() {
    this.productService.getProducts(this.appState.productState.keyword, this.appState.productState.currentPage, this.appState.productState.limit)
      .subscribe({
        next: response => {
          // @ts-ignore
          this.appState.productState.products = response.body as Product[]
          let totalProducts: number = parseInt(response.headers.get("X-Total-Count")!)
          this.appState.productState.totalPages = Math.floor(totalProducts / this.appState.productState.limit)
          if (totalProducts % this.appState.productState.limit != 0) {
            this.appState.productState.totalPages++;
          }
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
          this.appState.productState.products = this.appState.productState.products
            .filter((p :any)  => p.id != product.id)
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
    this.appState.productState.currentPage = number
    this.getProducts()
  }

  modify(product: Product) {
    this.router.navigateByUrl(`/editProduct/${product.id}`)
  }
}
