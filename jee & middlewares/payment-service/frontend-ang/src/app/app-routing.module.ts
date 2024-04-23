import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoadStudentComponent} from "./load-student/load-student.component";
import {LoadPaymentComponent} from "./load-payment/load-payment.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {StudentsComponent} from "./students/students.component";
import {LoginComponent} from "./login/login.component";
import {ProfileComponent} from "./profile/profile.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {AuthGuard} from "./guards/auth.guard";
import {PaymentsComponent} from "./payments/payments.component";
import {PaymentDetailsComponent} from "./payment-details/payment-details.component";
import {StudentDetailsComponent} from "./student-details/student-details.component";
import {AddPaymentComponent} from "./add-payment/add-payment.component";

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "login", component: LoginComponent},
  {path: "admin", component: AdminTemplateComponent,

    children: [
      {path: "home", component: HomeComponent},
      {path: "profile", component: ProfileComponent},
      {path: "loadStudent", component: LoadStudentComponent},
      {path: "loadPayment", component: LoadPaymentComponent},
      {path: "dashboard", component: DashboardComponent},
      {path: "students", component: StudentsComponent},
      {path: "payments", component: PaymentsComponent},
      {path: "payments/:id", component: PaymentDetailsComponent},
      {path: "students/:studentCode", component: StudentDetailsComponent},
      {path: "students/:studentCode/newPayment", component: AddPaymentComponent}
    ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
