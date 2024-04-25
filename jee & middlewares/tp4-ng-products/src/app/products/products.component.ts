import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products! : Array<any>
  ngOnInit(): void {
    this.products = new Array(10)
      for (let i = 0; i < 10; i++) {
        this.products[i] = {
          id: i + 1,
          name: "product-" + i,
          price: (i+1) * 20.5,
          checked: false
        }
    }
  }

  checkProduct(product: any) {
    product.checked = !product.checked
  }
}
