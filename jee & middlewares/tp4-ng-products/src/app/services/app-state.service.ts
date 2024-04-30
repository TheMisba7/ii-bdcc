import { Injectable } from '@angular/core';
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  public productState = {
    products: [] as Product[],
    keyword: "",
    limit: 2,
    currentPage: 1,
    totalPages: 0,
    totalProducts: 0,
    status: "",
    errorMessage: ""
  }
  constructor() { }
  setProductState(state :any) {
    this.productState = {...this.productState, ...state}
  }
}
