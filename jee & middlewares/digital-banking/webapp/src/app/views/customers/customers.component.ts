import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {
  ButtonDirective,
  ColComponent,
  ContainerComponent, FormControlDirective,
  InputGroupComponent,
  InputGroupTextDirective, PageItemDirective, PageLinkDirective, PaginationComponent, ProgressComponent,
  RowComponent, TableDirective, TemplateIdDirective, WidgetStatCComponent
} from "@coreui/angular";
import {IconDirective} from "@coreui/icons-angular";
import {Customer, Page} from "../../../model/model";
import {CustomerServiceService} from "../../services/customer-service.service";
import {NgForOf} from "@angular/common";
import {Router, RouterLink} from "@angular/router";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort, MatSortModule} from "@angular/material/sort";
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable,
  MatTableDataSource
} from "@angular/material/table";
import {MatCard, MatCardContent, MatCardHeader, MatCardModule} from "@angular/material/card";
import {MatDivider} from "@angular/material/divider";
import {FormsModule} from "@angular/forms";
import {debounceTime, distinctUntilChanged, fromEvent} from "rxjs";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [ContainerComponent, RowComponent, ColComponent, InputGroupComponent, InputGroupTextDirective, IconDirective, FormControlDirective, ButtonDirective, TableDirective, ProgressComponent, TemplateIdDirective, WidgetStatCComponent, NgForOf, PaginationComponent, PageItemDirective, RouterLink, PageLinkDirective, MatCardModule, MatCardHeader, MatDivider, MatCardContent, MatTable, MatColumnDef, MatHeaderCell, MatCell, MatPaginator, MatSortModule, MatHeaderCellDef, MatCellDef, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, FormsModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.scss'
})
export class CustomersComponent implements OnInit{
  public customers! :Customer[]
  public size :number = 100
  public page :number = 1
  public totalPages! :number
  public totalElements! :number
  public keyword :string = ""
  public pageContainer!: Page;
  public datasource: any
  displayedColumns: string[] = ['id', 'firstname', 'lastname', 'email'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('searchInput', { static: true }) searchInput!: ElementRef;


  constructor(private customerService: CustomerServiceService) {
  }
    ngOnInit(): void {
        this.getCustomers()
      fromEvent(this.searchInput.nativeElement, 'keyup').pipe(
        // Map the event to the input value
        map((event: any) => event.target.value),
        // Add a delay
        debounceTime(300),
        // Only emit if the current value is different from the last
        distinctUntilChanged()
      ).subscribe((keyword: string) => {
        this.keyword = keyword;
        this.search();
      });
    }

  private getCustomers() {
    this.customerService.getCustomers(this.page, this.size, this.keyword)
      .subscribe({
        next: result => {
          this.pageContainer = result
          this.datasource = new MatTableDataSource(this.pageContainer.content)
          this.datasource.paginator = this.paginator
          this.datasource.sort = this.sort
        },
        error: err => {
          console.log(err)
        }
        }
      )
  }

  public search() {
    this.page = 0
    this.getCustomers()
  }

}
