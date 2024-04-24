import {Component, ElementRef, OnInit, Renderer2} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrl: './payment-details.component.css'
})
export class PaymentDetailsComponent implements OnInit{
  public payment :any
  statusList: string[] = ["CREATED", "REJECTED", "VALIDATED"];
  isLoading: boolean = false
  constructor(private router: ActivatedRoute, private https: HttpClient, private renderer: Renderer2, private elementRef: ElementRef) {
  }
    ngOnInit(): void {
        this.router.params.subscribe(params => {
          let paymentId = params["id"]
          this.https.get("http://localhost:9191/api/payments/" + paymentId)
            .subscribe({
              next: data => {
                this.payment = data
              },
              error: err => {
                console.error(err)
              }
            })
        })
    }

  updateStatus(id: number, status: string) {
    this.isLoading = true
    this.https.put("http://localhost:9191/api/payments/" + id + "/status?newStatus=" + status, null)
      .subscribe({
        next: value => {
          this.isLoading = false
        },
        error: err => {
          console.error(err)
          this.isLoading = false
        }
      })
  }

  downloadReceipt(paymentId: any) {
    this.https.get("http://localhost:9191/api/payments/" + paymentId + "/receipt", { responseType: 'blob' })
      .subscribe(blob => {
        const fileURL = URL.createObjectURL(blob);
        window.open(fileURL, '_blank');
      },
        error => {console.error(error)});
  }
}
