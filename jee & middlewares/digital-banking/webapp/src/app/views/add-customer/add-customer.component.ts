import {Component, OnInit} from '@angular/core';
import {MatCardModule, MatCardHeader} from "@angular/material/card";
import {MatDivider} from "@angular/material/divider";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {RowComponent} from "@coreui/angular";
import {MatButton} from "@angular/material/button";
import {AgentRole, Customer} from "../../../model/model";
import {CustomerServiceService} from "../../services/customer-service.service";
import {Router} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [
    MatCardHeader,
    MatCardModule,
    MatDivider,
    MatFormField,
    MatInput,
    MatSelect,
    ReactiveFormsModule,
    MatLabel,
    RowComponent,
    MatButton,
    MatOption,
    NgForOf
  ],
  templateUrl: './add-customer.component.html',
  styleUrl: './add-customer.component.scss'
})
export class AddCustomerComponent implements OnInit {
  public customerForm!: FormGroup
  public roles!: AgentRole[]

  constructor(private fb: FormBuilder, private customerService: CustomerServiceService, private route :Router) {
  }
  ngOnInit(): void {
    this.getRoles()
    this.customerForm = this.fb.group({
      firstname : this.fb.control(''),
      lastname : this.fb.control(''),
      email : this.fb.control(''),
      role: this.fb.control('')
    })
  }

  handleNewCustomer() {
    // @ts-ignore
    let customer :  Customer = {
      firstname: this.customerForm.value.firstname,
      lastname: this.customerForm.value.lastname,
      email: this.customerForm.value.email,
      roleIds: [this.customerForm.value.role]
    }
    this.customerService.createCustomer(customer)
      .subscribe({
        next: re => {
          this.route.navigateByUrl("/customers")
        },
        error: err => {
          console.log(err)
        }
      })
  }

  getRoles() {
    this.customerService.getRoles()
      .subscribe({
        next: value => {
          this.roles = value
        },
        error: err => {
          console.log(err)
        }
      })
  }
}
