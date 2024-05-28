import {Component, OnInit, ViewChild} from '@angular/core';
import {BankAccount} from "../../../model/model";
import {AccountService} from "../../services/account.service";
import {ActivatedRoute} from "@angular/router";
import {
  ButtonCloseDirective,
  ButtonDirective,
  ContainerComponent, FormControlDirective, FormDirective, FormLabelDirective, FormSelectDirective,
  ModalBodyComponent,
  ModalComponent, ModalFooterComponent,
  ModalHeaderComponent, ModalTitleDirective,
  TableDirective
} from "@coreui/angular";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from "@angular/material/card";
import {MatDivider} from "@angular/material/divider";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {NgIf} from "@angular/common";
import {MatFabButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable, MatTableDataSource
} from "@angular/material/table";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatIcon} from "@angular/material/icon";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-account-details',
  standalone: true,
  imports: [
    ContainerComponent,
    MatProgressSpinner,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatDivider,
    MatCardContent,
    MatFormField,
    MatSelect,
    MatOption,
    MatCardActions,
    NgIf,
    TableDirective,
    MatCardSubtitle,
    MatLabel,
    MatFabButton,
    MatTooltip,
    MatTable,
    MatSort,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatCellDef,
    MatHeaderCellDef,
    MatHeaderRow,
    MatPaginator,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatSortHeader,
    MatIcon,
    ModalComponent,
    ModalHeaderComponent,
    ModalBodyComponent,
    ModalFooterComponent,
    ButtonDirective,
    ModalTitleDirective,
    ButtonCloseDirective,
    FormDirective,
    FormLabelDirective,
    FormControlDirective,
    FormSelectDirective,
    ReactiveFormsModule
  ],
  templateUrl: './account-details.component.html',
  styleUrl: './account-details.component.scss'
})
export class AccountDetailsComponent implements OnInit{
  public account!: BankAccount
  isLoading: boolean = false;
  statusList: string [] = ["CREATED", "ACTIVATED", "SUSPENDED"];
  @ViewChild('operationPaginator', {static: true}) operationPaginator!: MatPaginator;
  @ViewChild('operationSortSort', {read: MatSort, static: true}) operationSortSort!: MatSort;
  datasource: any;
  displayedColumns: string [] = ["id", "createdAt", "amount", "type", "description"];
  visible: boolean = false
  operationForm!: FormGroup;

  constructor(private accountService: AccountService, private activeRoute: ActivatedRoute, private fb: FormBuilder) {}
    ngOnInit(): void {
      this.getAccount()
      this.operationForm = this.fb.group({
        operationType: this.fb.control(null),
        amount: this.fb.control(0),
        description: this.fb.control(null),
      })
    }

  private getAccount() {
    this.activeRoute.params.subscribe({
      next: params => {
        let accountId = params["accountId"]
        this.accountService.getAccount(accountId)
          .subscribe({
            next: res => {
              this.account = res
              this.datasource = new MatTableDataSource(this.account.operations)
              this.datasource.paginator = this.operationPaginator
              this.datasource.sort = this.operationSortSort
            },
            error: err => {
              console.log(err)
            }
          })
      }
    })
  }

  public updateStatus(accountId: string, status: string) {
    this.isLoading = true
    this.accountService.updateStatus(accountId, status)
      .subscribe({
        next: res => {
          this.isLoading = false
        },
        error: err => {
          console.log(err)
        }
      })
  }

  addOperation(id: string) {

  }

  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  submitOperation() {
    console.log(this.account.id)
    console.log(this.operationForm.value.operationType)
    console.log(this.operationForm.value.amount)
    console.log(this.operationForm.value.description)
  }
}
