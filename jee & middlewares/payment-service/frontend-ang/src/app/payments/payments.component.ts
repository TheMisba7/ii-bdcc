import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit{
  public payments: any
  public datasource: any
  displayedColumns: string[] = ['id', 'type', 'status', 'amount', 'student', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private https: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.https.get("http://localhost:9191/api/payments")
      .subscribe(
        {
          next: value => {
            this.payments = value
            this.datasource = new MatTableDataSource(this.payments)
            this.datasource.paginator = this.paginator
            this.datasource.sort = this.sort
          },
          error: err => {
            console.error(err)
          }
        }
      )
    }

  getPaymentDetails(id: number) {
    this.router.navigate(["/admin/payments", id])
  }
}
