import {Component, OnInit} from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardGroupComponent,
  ColComponent,
  ContainerComponent, FormControlDirective, FormDirective, InputGroupComponent, InputGroupTextDirective, RowComponent
} from "@coreui/angular";
import {IconDirective} from "@coreui/icons-angular";
import {NgStyle} from "@angular/common";
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ButtonDirective,
    CardBodyComponent,
    CardComponent,
    CardGroupComponent,
    ColComponent,
    ContainerComponent,
    FormControlDirective,
    FormDirective,
    IconDirective,
    InputGroupComponent,
    InputGroupTextDirective,
    RowComponent,
    NgStyle,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  public loginForm!: any;

  constructor(private authService: AuthService, private fb: FormBuilder, private route: Router) {
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated) this.route.navigateByUrl("/dashboard")
    this.loginForm = this.fb.group({
      username: this.fb.control(''),
      password: this.fb.control('')
    })
  }

  login() {
    let username = this.loginForm.value.username
    let password = this.loginForm.value.password
    this.authService.login(username, password).subscribe({
      next: re => this.route.navigateByUrl("/dashboard"),
      error: err => {console.log(err)}
    });
  }
}
