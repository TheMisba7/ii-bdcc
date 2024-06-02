import {Injectable, OnInit} from '@angular/core';
import {Customer} from "../../model/model";
import {HttpClient} from "@angular/common/http";
import {map, tap} from "rxjs/operators";
import {CustomerServiceService} from "./customer-service.service";
import {Router} from "@angular/router";
import {Observable, of, Subject} from "rxjs";

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
  constructor(private https: HttpClient, private customerService: CustomerServiceService, private route: Router) {
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
    return this.customerService.getConnectedCustomer()
      .pipe(map( cust => {
        this._customer = cust
        return cust
      }))
  }
  public get expiresIn(): number {
    return this._expiresIn;
  }
  public get token(): string {
    return this._token;
  }
  public get customer(): Customer {
    if (this._isAuthenticated && this._customer == undefined) {
      this.getConnectedUser()
    }
    return this._customer;
  }

  public get isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  public isAdmin(): Observable<boolean> {
    return this.isRolePresent("ADMIN")
  }

  private isRolePresent(roleName: string): Observable<boolean> {
    if (this.isAuthenticated && this._customer == null) {
      return this.getConnectedUser()
        .pipe(
          map(re => {
            for (let role of this._customer.roles) {
              if (roleName === role.name)
                return true
            }
            return false
          })
        )
    }
    if (this._isAuthenticated && this._customer != null) {
      for (let role of this._customer.roles) {
        if (roleName === role.name)
          return of(true)
      }
    }
    return of(false)
  }
  isCustomer() {
   return this.isRolePresent("CUSTOMER")
  }

  logout() {
    localStorage.clear()
    this._isAuthenticated = false
    this.route.navigateByUrl("/login")
  }
}
