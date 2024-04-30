import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Router} from "@angular/router";
import {AppStateService} from "../services/app-state.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

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
          let products = response.body as Product[]
          let totalProducts: number = parseInt(response.headers.get("X-Total-Count")!)
          let totalPages = Math.floor(totalProducts / this.appState.productState.limit)
          if (totalProducts % this.appState.productState.limit != 0) {
            totalPages++;
          }
          this.appState.setProductState({
            products: products,
            totalProducts: totalProducts,
            totalPages: totalPages,
          })
        },
        error: err => {
          this.appState.setProductState({
            status: "ERROR",
            errorMessage: err.message
          })
        }
      })
  }
  toggleCheck(product: Product) {
    this.productService.toggleCheck(product)
      .subscribe({
        next: updatedProduct => {
          product.checked = !product.checked
          this.appState.setProductState({
          })
        },
        error: err => {
          this.appState.setProductState({
            status: "ERROR",
            errorMessage: err.message
          })
        }
      })
  }

  delete(product: Product) {
    this.appState.setProductState({
    })
    this.productService.delete(product.id)
      .subscribe({
        next: res => {
          this.appState.setProductState({
            products: this.appState.productState.products.filter((p :any)  => p.id != product.id),
          })
        },
        error: err => {
          this.appState.setProductState({
            errorMessage: err.message
          })
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
