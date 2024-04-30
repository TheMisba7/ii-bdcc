import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../services/product.service";

@Component({
  selector: 'app-edit-products',
  templateUrl: './edit-products.component.html',
  styleUrl: './edit-products.component.css'
})
export class EditProductsComponent implements OnInit{
  public product! :Product
  editProductForm!: FormGroup;

  constructor(private fb :FormBuilder, private activeRoute :ActivatedRoute,
              private productService: ProductService, private router :Router) {
  }
  ngOnInit(): void {
    this.activeRoute.params.subscribe(params => {
      let productId = params["productId"]
      this.productService.getProductById(productId)
        .subscribe({
          next: res => {
            this.product = res
            this.editProductForm = this.fb.group({
              id : this.fb.control(this.product.id),
              name : this.fb.control(this.product.name, Validators.required),
              price : this.fb.control(this.product.price, Validators.required),
              checked : this.fb.control(this.product.checked)
            })
          },
          error: err => {console.log(err)}
        })
    })

  }

  save() {
    let updatedProduct :Product = this.editProductForm.value
    this.productService.update(updatedProduct)
      .subscribe({
        next: res => {
          this.router.navigateByUrl("/products")
        },
        error: err => {
          console.log(err)
        }
      })
  }
}
