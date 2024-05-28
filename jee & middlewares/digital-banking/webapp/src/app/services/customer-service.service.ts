import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Customer, CustomerDetails, Page} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {
  public host : string = "http://localhost:9991/api/customers"

  constructor(private https: HttpClient) {}

  public getCustomers(page: number, size: number, keyword: string) {
    return this.https.get<Page>(this.host + "?page=" + page + "&size=" + size + "&keyword=" + keyword)
  }

  public createCustomer(customer: Customer) {
    return this.https.post(this.host, customer);
  }

  getCustomerDetails(customerId: number) {
    return this.https.get<CustomerDetails>(this.host + "/" + customerId + "/details")
  }
}
