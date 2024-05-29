import {Injectable, OnInit} from '@angular/core';
import {Customer} from "../../model/model";
import {HttpClient} from "@angular/common/http";
import {result} from "lodash-es";
import {tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public host : string = "http://localhost:9991/login"
  private _customer!: Customer
  private _token!: string
  private _expiresIn!: number
  private _isAuthenticated: boolean = false
  private authTokenKey = "auth-token";
  constructor(private https: HttpClient) {
    // @ts-ignore
    this._token = localStorage.getItem(this.authTokenKey)
    this._isAuthenticated = this._token != undefined
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
        }));
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
}
