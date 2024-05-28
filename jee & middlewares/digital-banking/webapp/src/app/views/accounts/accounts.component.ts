import {Component, OnInit, ViewChild} from '@angular/core';
import {ColComponent, ContainerComponent, RowComponent} from "@coreui/angular";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatDivider} from "@angular/material/divider";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable, MatTableDataSource
} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatMiniFabButton} from "@angular/material/button";
import {MatPaginator} from "@angular/material/paginator";
import {Page} from "../../../model/model";
import {AccountService} from "../../services/account.service";
import {Router} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    ContainerComponent,
    MatCard,
    MatCardHeader,
    MatDivider,
    MatCardTitle,
    MatIcon,
    MatCardContent,
    MatTable,
    MatSort,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderCellDef,
    MatCell,
    MatCellDef,
    MatMiniFabButton,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatPaginator,
    MatHeaderRowDef,
    RowComponent,
    ColComponent
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.scss'
})
export class AccountsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'type', 'status', 'balance', 'customer', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  public size :number = 100
  public page :number = 1
  public totalPages! :number
  public totalElements! :number
  public pageContainer!: Page;
  datasource: any;

  constructor(private accountService: AccountService, private route: Router) {}
  ngOnInit(): void {
      this.getAccounts()
  }

  private getAccounts() {
    this.accountService.getAccounts(this.page, this.size)
      .subscribe({
        next: result => {
          this.pageContainer = result as Page
          this.datasource = new MatTableDataSource(this.pageContainer.content)
          this.datasource.paginator = this.paginator
          this.datasource.sort = this.sort
        }
      })
  }

  getAccountDetails(id: string) {
    this.route.navigateByUrl("/accounts/" + id)
  }
}
