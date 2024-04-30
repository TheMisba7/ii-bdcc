import { Component } from '@angular/core';
import {AppStateService} from "../services/app-state.service";
import {SpinnerService} from "../services/spinner.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  actions :Array<any> = [
    {"title": "Home", "icon": "house-door-fill", "route": "/home"},
    {"title": "Products", "icon": "home", "route": "/products"},
    {"title": "New Product", "icon": "plus-circle-fill", "route": "/new-product"}
  ]
  currentAction: any

  constructor(public appState: AppStateService, public spinner: SpinnerService) {
  }
  updateAction(action: any) {
    this.currentAction = action
  }

}
