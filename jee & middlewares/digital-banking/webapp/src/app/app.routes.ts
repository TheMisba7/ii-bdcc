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
      },
      {
        path: 'theme',
        loadChildren: () => import('./views/theme/routes').then((m) => m.routes)
      },
      {
        path: 'base',
        loadChildren: () => import('./views/base/routes').then((m) => m.routes)
      },
      {
        path: 'buttons',
        loadChildren: () => import('./views/buttons/routes').then((m) => m.routes)
      },
      {
        path: 'forms',
        loadChildren: () => import('./views/forms/routes').then((m) => m.routes)
      },
      {
        path: 'icons',
        loadChildren: () => import('./views/icons/routes').then((m) => m.routes)
      },
      {
        path: 'notifications',
        loadChildren: () => import('./views/notifications/routes').then((m) => m.routes)
      },
      {
        path: 'widgets',
        loadChildren: () => import('./views/widgets/routes').then((m) => m.routes)
      },
      {
        path: 'charts',
        loadChildren: () => import('./views/charts/routes').then((m) => m.routes)
      },
      {
        path: 'pages',
        loadChildren: () => import('./views/pages/routes').then((m) => m.routes)
      }
    ]
  },
  {
    path: '404',
    loadComponent: () => import('./views/pages/page404/page404.component').then(m => m.Page404Component),
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    loadComponent: () => import('./views/pages/page500/page500.component').then(m => m.Page500Component),
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    loadComponent: () => import('./views/pages/login/login.component').then(m => m.LoginComponent),
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    loadComponent: () => import('./views/pages/register/register.component').then(m => m.RegisterComponent),
    data: {
      title: 'Register Page'
    }
  },
  { path: '**', redirectTo: 'dashboard' }
];
