import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BankAccount, Operation} from "../../model/model";
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  public host : string = "http://localhost:9991/api/accounts"
  constructor(private https: HttpClient) {}

  public getAccounts(page: number, size: number) {
    return this.https.get(this.host + "?page" + page + "&size=" + size)
  }

  public getAccount(accountId: string) {
    return this.https.get<BankAccount>(this.host + "/" + accountId)
  }

  public  updateStatus(accountId: string, status: string) {
    return this.https.put<BankAccount>(this.host + "/" + accountId + "/status?newStatus=" + status, null)
  }

  public postOperation(operation: Operation) {
    return this.https.post(this.host + "/operation", operation)
  }
}
