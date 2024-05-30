import {Injectable, OnInit} from '@angular/core';
import {Customer} from "../../model/model";
import {HttpClient} from "@angular/common/http";
import {result} from "lodash-es";
import {tap} from "rxjs/operators";
import {CustomerServiceService} from "./customer-service.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit{
  public host : string = "http://localhost:9991/login"
  private _customer!: Customer
  private _token!: string
  private _expiresIn!: number
  private _isAuthenticated: boolean = false
  private authTokenKey = "auth-token";
  constructor(private https: HttpClient, private customerService: CustomerServiceService) {
    // @ts-ignore
    this._token = localStorage.getItem(this.authTokenKey)
    this._isAuthenticated = this._token != undefined
  }

  ngOnInit(): void {
    if (this.isAuthenticated) {
      this.getConnectedUser()
    }
    }

  public login(email: string, password: string) {
   return  this.https.post(this.host, {
      "username": email,
      "password": password
    }).pipe(
        tap((result: any) => {
          this._token = result.token;
          this._expiresIn = result.expiresIn;
          this._isAuthenticated = true;
          localStorage.setItem(this.authTokenKey, this._token);
          this.getConnectedUser()
        }));
  }

  private getConnectedUser() {
    this.customerService.getConnectedCustomer()
      .subscribe({
        next: cust => {
          this._customer = cust
        },
        error: err => {
          console.log(err)
        }
      })
  }
  public get expiresIn(): number {
    return this._expiresIn;
  }
  public get token(): string {
    return this._token;
  }
  public get customer(): Customer {
    return this._customer;
  }

  public get isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  public isAdmin(): boolean {
    return this.isRolePresent("ADMIN")
  }

  private isRolePresent(roleName: string): boolean {
    if (this.isAuthenticated && this._customer == null) {
      this.getConnectedUser()
    }
    console.log(this._customer)
    if (this._isAuthenticated && this._customer != null) {
      for (let role of this._customer.roles) {
        if (roleName === role.name)
          return true
      }
    }
    return false
  }
  isCustomer() {
   return this.isRolePresent("CUSTOMER")
  }
}
