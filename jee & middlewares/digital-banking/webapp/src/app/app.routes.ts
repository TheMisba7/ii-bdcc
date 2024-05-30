import { Routes } from '@angular/router';
import { DefaultLayoutComponent } from './layout';
import {CustomersComponent} from "./views/customers/customers.component";
import {AuthGuard} from "./guards/auth-guard.guard";
import {LoginComponent} from "./views/login/login.component";

export const routes: Routes = [
  {
    path: "login",
    component: LoginComponent,
    data: {
      title: "Login"
    }
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },

  {
    path: '',
    component: DefaultLayoutComponent, canActivate: [AuthGuard],
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./views/dashboard/routes').then((m) => m.routes)
      },
      {
        path: 'profile',
        loadComponent: () => import('./views/profile/profile.component').then((m) => m.ProfileComponent)
      },
      {
        path: 'customers',
        loadComponent: () => import('./views/customers/customers.component').then(m => m.CustomersComponent),
        data: {
          title: 'Customers'
        }
      },
      {
        path: 'customers/:customerId',
        loadComponent: () => import('./views/customer-details/customer-details.component').then(m => m.CustomerDetailsComponent),
        data: {
          title: 'Accounts'
        }
      },
      {
        path: 'add-customer',
        loadComponent: () => import('./views/add-customer/add-customer.component').then(m => m.AddCustomerComponent),
        data: {
          title: 'Add Customer'
        }
      },
      {
        path: 'accounts',
        loadComponent: () => import('./views/accounts/accounts.component').then(m => m.AccountsComponent),
        data: {
          title: 'Accounts'
        }
      },
      {
        path: 'accounts/:accountId',
        loadComponent: () => import('./views/account-details/account-details.component').then(m => m.AccountDetailsComponent),
        data: {
          title: 'Accounts'
        }
      }
    ]
  },
  { path: '**', redirectTo: 'dashboard' }
];
