import {AsyncPipe, NgIf, NgStyle} from '@angular/common';
import { Component, OnInit} from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import {
  AvatarComponent,
  ButtonDirective,
  ButtonGroupComponent,
  CardBodyComponent,
  CardComponent,
  CardFooterComponent, CardHeaderActionsComponent,
  CardHeaderComponent,
  ColComponent, ContainerComponent,
  FormCheckLabelDirective,
  GutterDirective,
  ProgressBarDirective,
  ProgressComponent,
  RowComponent,
  TableDirective, TemplateIdDirective,
  TextColorDirective, WidgetStatFComponent
} from '@coreui/angular';
import { ChartjsComponent } from '@coreui/angular-chartjs';
import { IconDirective } from '@coreui/icons-angular';
import {DashboardService} from "../../services/dashboard.service";
import {AgentRole, Dashboard} from "../../../model/model";
import {AuthService} from "../../services/auth.service";
import {MatLabel} from "@angular/material/form-field";
import {RouterLink} from "@angular/router";
import {cilList, cilMoney, cilPeople, cilTransfer} from "@coreui/icons";


@Component({
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss'],
  standalone: true,
  imports: [TextColorDirective, CardComponent, CardBodyComponent, RowComponent, ColComponent, ButtonDirective, IconDirective, ReactiveFormsModule, ButtonGroupComponent, FormCheckLabelDirective, ChartjsComponent, NgStyle, CardFooterComponent, GutterDirective, ProgressBarDirective, ProgressComponent, CardHeaderComponent, TableDirective, AvatarComponent, NgIf, WidgetStatFComponent, TemplateIdDirective, ContainerComponent, MatLabel, CardHeaderActionsComponent, RouterLink, AsyncPipe]
})
export class DashboardComponent implements OnInit {
  public dashboard!: Dashboard;


  constructor(private dashboardService: DashboardService, public authService: AuthService) {}
  ngOnInit(): void {
    this.getDashboard()
  }


  private getDashboard() {
    this.dashboardService.getDashboard()
      .subscribe({
        next: res => {
          this.dashboard = res
        },
        error: err => {
          console.log(err)
        }
      })
  }

  getRoles(roles: AgentRole[]) {
    let stringRoles: String = "";
    for (let role of roles) {
      if (stringRoles === "") {
        stringRoles = role.name
      } else {
        stringRoles = stringRoles.concat(" | " + role.name)
      }
    }
    return stringRoles;
  }

  protected readonly cilList = cilList;
  protected readonly cilPeople = cilPeople;
  protected readonly cilMoney = cilMoney;
  protected readonly cilTransfer = cilTransfer;
}
