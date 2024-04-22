import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  public formLogin!: FormGroup
  constructor(private fb : FormBuilder, private authService: AuthService,
              private router: Router) {
  }
    ngOnInit(): void {
        this.formLogin = this.fb.group({
          username : this.fb.control(''),
          password : this.fb.control('')
        })
    }

  login() {
    let username = this.formLogin.value.username
    let password = this.formLogin.value.password
    this.authService.login(username, password)
    this.router.navigateByUrl("/admin")
  }
}
