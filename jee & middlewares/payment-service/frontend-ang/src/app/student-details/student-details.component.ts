import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit{
  public studentDashboard :any
  public datasource: any
  displayedColumns: string[] = ['id', 'paidAt', 'status', 'type', 'amount','actions'];
  @ViewChild('studentPaymentPaginator', {static: true}) studentPaymentPaginator!: MatPaginator;
  @ViewChild('studentPaymentSort', {read: MatSort, static: true}) studentPaymentSort!: MatSort;

  constructor(private https: HttpClient, private activeRoute :ActivatedRoute, private router: Router) {
  }
    ngOnInit(): void {
        this.activeRoute.params.subscribe(params => {
          let studentCode = params["studentCode"]
          this.https.get("http://localhost:9191/api/students/" + studentCode + "/dashboard")
            .subscribe({
              next: data => {
                this.studentDashboard = data
                this.datasource = new MatTableDataSource(this.studentDashboard.payments)
                this.datasource.paginator = this.studentPaymentPaginator
                this.datasource.sort = this.studentPaymentSort
              },
              error: err => {
                console.log(err)
              }
            })
        })
    }

  getPaymentDetails(id: number) {
    this.router.navigate(["/admin/payments", id])
  }

  addNewPayment(studentCode: number) {
    this.router.navigate([`/admin/students/${studentCode}/newPayment`]);
  }
}
