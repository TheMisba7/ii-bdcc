import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit{
  public students: any
  public filterValue! :string
  public datasource: any
  displayedColumns: string[] = ['code', 'firstname', 'lastname', 'email', 'majorId','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private https: HttpClient, private router :Router) {
  }
  ngOnInit(): void {
    this.https.get("http://localhost:9191/api/students")
      .subscribe({
        next: data => {
          this.students = data
          this.datasource = new MatTableDataSource(this.students)
          this.datasource.sort = this.sort
          this.datasource.paginator = this.paginator
          this.datasource.filterPredicate = function(dataFilter: any, filter: string): boolean {
            return dataFilter.firstname.toLowerCase().includes(filter)
              || dataFilter.lastname.toLowerCase().includes(filter);
          };
        },
        error: err => {
          console.log(err)
        }
      })
  }

  applyFilter() {
    this.filterValue = this.filterValue.trim(); // Remove whitespace
    this.filterValue = this.filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.datasource.filter = this.filterValue;
  }

  getStudentDashboard(code: string) {
    this.router.navigate(["/admin/students", code])
  }
}
