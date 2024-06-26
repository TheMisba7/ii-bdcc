import { Component } from '@angular/core';
import {AppStateService} from "../services/app-state.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  constructor(public appState :AppStateService) {
  }

  getCheckedProductsNbr() {
    return this.appState.productState.products
      .filter(p => p.checked).length
  }
}
