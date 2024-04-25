import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-products',
  templateUrl: './new-products.component.html',
  styleUrl: './new-products.component.css'
})
export class NewProductsComponent implements OnInit{
  public formGroup! :FormGroup
  constructor(private productService :ProductService, private fb: FormBuilder, private router :Router) {
  }

  ngOnInit(): void {
        this.formGroup = this.fb.group({
          name : this.fb.control('', Validators.required),
          price : this.fb.control('0', Validators.required),
          checked : this.fb.control('')
        })
    }

  save() {
    let newProduct = this.formGroup.value
    this.productService.save(newProduct)
      .subscribe({
        next: res => {
          this.router.navigateByUrl("/products")
        },
        error: err => {
          console.error(err)
        }
      })
  }
}
