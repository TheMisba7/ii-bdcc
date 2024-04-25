import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  actions :Array<any> = [
    {"title": "Home", "icon": "house-door-fill", "route": "/home"},
    {"title": "Products", "icon": "home", "route": "/products"},
    {"title": "New Product", "icon": "plus-circle-fill", "route": "/new-product"}
  ]
  currentAction: any

  updateAction(action: any) {
    this.currentAction = action
  }
}
