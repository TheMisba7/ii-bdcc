import {Component, OnInit, ViewChild} from '@angular/core';
import {
  ButtonCloseDirective,
  ButtonDirective, ColComponent,
  ContainerComponent,
  FormControlDirective,
  FormDirective,
  FormLabelDirective,
  FormSelectDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent, ModalTitleDirective, RowComponent, TableDirective
} from "@coreui/angular";
import {
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle
} from "@angular/material/card";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow, MatRowDef, MatTable, MatTableDataSource
} from "@angular/material/table";
import {MatDivider} from "@angular/material/divider";
import {MatFabButton, MatMiniFabButton} from "@angular/material/button";
import {MatFormField} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatOption} from "@angular/material/autocomplete";
import {MatPaginator} from "@angular/material/paginator";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {MatSelect} from "@angular/material/select";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {MatTooltip} from "@angular/material/tooltip";
import {NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {BankAccount, Customer, PostAccountRQ} from "../../../model/model";
import {AccountService} from "../../services/account.service";
import {CustomerServiceService} from "../../services/customer-service.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";

@Component({
  selector: 'app-customer-details',
  standalone: true,
  imports: [
    ButtonCloseDirective,
    ButtonDirective,
    ContainerComponent,
    FormControlDirective,
    FormDirective,
    FormLabelDirective,
    FormSelectDirective,
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle,
    MatCell,
    MatCellDef,
    MatColumnDef,
    MatDivider,
    MatFabButton,
    MatFormField,
    MatHeaderCell,
    MatHeaderRow,
    MatHeaderRowDef,
    MatIcon,
    MatOption,
    MatPaginator,
    MatProgressSpinner,
    MatRow,
    MatRowDef,
    MatSelect,
    MatSort,
    MatSortHeader,
    MatTable,
    MatTooltip,
    ModalBodyComponent,
    ModalComponent,
    ModalFooterComponent,
    ModalHeaderComponent,
    ModalTitleDirective,
    NgIf,
    ReactiveFormsModule,
    TableDirective,
    MatHeaderCellDef,
    RowComponent,
    ColComponent,
    IconDirective,
    RouterLink,
    MatMiniFabButton
  ],
  templateUrl: './customer-details.component.html',
  styleUrl: './customer-details.component.scss'
})
export class CustomerDetailsComponent implements OnInit{
  public customer!: Customer
  public customerAccounts!: BankAccount[]
  public visible: boolean = false
  datasource: any;
  displayedColumns: string [] = ["id", "createdAt", "balance", "type", "status", "actions"];
  accountForm!: FormGroup;
  isLoading: boolean = false;
  @ViewChild('accountPaginator', {static: true}) accountPaginator!: MatPaginator;
  @ViewChild('accountSort', {read: MatSort, static: true}) accountSort!: MatSort;
  constructor(private accountService: AccountService,
              private customerService: CustomerServiceService,
              private fb: FormBuilder, private activeRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activeRoute.params.subscribe({
      next: params => {
        let customerId = params["customerId"]
        this.getCustomerDetails(customerId)
      }})
    this.accountForm = this.fb.group({
      accountType: ['CURRENT', Validators.required],
      balance: ['0'],
      overDraft: ['10000', [Validators.required, Validators.min(10000)]],
      interestRate: ['1', [Validators.required, Validators.min(1)]]
    })
  }
  toggleLiveDemo() {
    this.visible = !this.visible
  }

  createAccount() {
    this.isLoading = true
    // @ts-ignore
    const account :PostAccountRQ = {
      type: this.accountForm.value.accountType,
      balance: this.accountForm.value.balance,
      customerId: this.customer.id
    }

    if (account.type == 'CURRENT') {
      account.overDraft = this.accountForm.value.overDraft
    } else {
      account.interestRate = this.accountForm.value.interestRate
    }

    this.accountService.createAccount(account)
      .subscribe({
        next: res => {
          this.customerAccounts.push(res)
          this.datasource = new MatTableDataSource(this.customerAccounts)
          this.datasource.paginator = this.accountPaginator
          this.datasource.sort = this.accountSort
          this.accountForm.reset()
          this.toggleLiveDemo()
          this.isLoading = false
        }
      })
  }

  private getCustomerDetails(customerId: any) {
    this.isLoading = true
    this.customerService.getCustomerDetails(customerId)
      .subscribe({
        next: res => {
          this.customer = res.customer
          this.customerAccounts = res.accounts
          this.datasource = new MatTableDataSource(res.accounts)
          this.datasource.paginator = this.accountPaginator
          this.datasource.sort = this.accountSort
          this.isLoading = false

        },
        error: err => {
          console.log(err)
        }
      })
  }
}
