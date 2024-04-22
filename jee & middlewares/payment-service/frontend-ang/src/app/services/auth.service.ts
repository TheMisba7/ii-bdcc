import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username! : string
  roles : string[] = []
  isAuthenticated! : boolean
  constructor() { }

  login(username: string, password: string) :boolean{
    this.username = username
    this.isAuthenticated =  true
    this.roles = ["admin"]
    return true
  }
}
