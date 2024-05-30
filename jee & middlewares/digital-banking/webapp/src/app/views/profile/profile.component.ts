import {Component, OnInit} from '@angular/core';
import {
  AvatarComponent,
  ButtonCloseDirective,
  ButtonDirective,
  ColComponent,
  ContainerComponent,
  FormControlDirective,
  FormDirective,
  FormLabelDirective,
  FormSelectDirective,
  ModalBodyComponent,
  ModalComponent, ModalFooterComponent, ModalHeaderComponent, ModalTitleDirective,
  RowComponent,
  TableDirective
} from "@coreui/angular";
import {MatCard, MatCardContent, MatCardHeader} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {NgIf} from "@angular/common";
import {AuthService} from "../../services/auth.service";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {CustomerServiceService} from "../../services/customer-service.service";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    ContainerComponent,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatFormField,
    MatOption,
    MatSelect,
    NgIf,
    TableDirective,
    RowComponent,
    AvatarComponent,
    ColComponent,
    ButtonDirective,
    ButtonCloseDirective,
    FormControlDirective,
    FormDirective,
    FormLabelDirective,
    FormSelectDirective,
    ModalBodyComponent,
    ModalComponent,
    ModalFooterComponent,
    ModalHeaderComponent,
    ModalTitleDirective,
    ReactiveFormsModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit{
  visible: boolean = false;
  changePasswordForm: any;

  constructor(public authService: AuthService, private fb: FormBuilder, private customerService: CustomerServiceService) {}

  ngOnInit(): void {
       this.changePasswordForm = this.fb.group({
         oldPassword: this.fb.control(["", Validators.minLength(8)]),
         newPassword: this.fb.control(["", Validators.minLength(8)])
       })
    }

  toggleModal() {
    this.visible = !this.visible
  }

  confirmChangePassword() {
    this.customerService.changePassword(
      this.changePasswordForm.value.oldPassword,
      this.changePasswordForm.value.newPassword
    ).subscribe({
      next: res => {
        this.authService.logout()
      },
      error: err => {
        console.log(err)
      }
    })
  }
}
