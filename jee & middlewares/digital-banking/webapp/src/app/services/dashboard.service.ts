import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Dashboard} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  public host : string = "http://localhost:9991/api/dashboard"

  constructor(private https: HttpClient) {}

  public getDashboard() {
    return this.https.get<Dashboard>(this.host)
  }
}
