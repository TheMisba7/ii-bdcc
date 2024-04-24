import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrl: './add-payment.component.css'
})
export class AddPaymentComponent implements OnInit{
  public formLogin!: FormGroup
  statusList: string[] = ["CREATED", "VALIDATED", "REJECTED"];
  typeList: string[] = ["CASH", "CHECK", "TRANSFER"];
  private studentCode!: string;
  constructor(private fb: FormBuilder, private https: HttpClient,
              private activatedRoute: ActivatedRoute, private router: Router) {
  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.studentCode = params["studentCode"]
    })
    this.formLogin = this.fb.group({
      paidDate : this.fb.control(''),
      amount : this.fb.control(''),
      status : this.fb.control(''),
      receipt : this.fb.control(''),
      paymentType : this.fb.control('')
    })
  }

  addNewPayment(target: any) {
    let paidDate = this.formLogin.value.paidDate
    let amount = this.formLogin.value.amount
    let status = this.formLogin.value.status
    let receipt = this.formLogin.value.receipt
    let paymentType = this.formLogin.value.paymentType
    let postPayment = new FormData()
    postPayment.append('file', target[0])
    postPayment.append('paidAt', paidDate)
    postPayment.append('amount', amount)
    postPayment.append('paymentType', paymentType)
    postPayment.append('status', status)
    postPayment.append('studentCode', this.studentCode)

    this.https.post("http://localhost:9191/api/payments", postPayment)
      .subscribe({
        next: data => {
          this.router.navigate(["/admin/students", this.studentCode])
        },
        error: err => {
          console.log(err)
        }
      })
    console.log(paidDate)
    console.log(amount)
    console.log(status)
    console.log(receipt)
  }
}
