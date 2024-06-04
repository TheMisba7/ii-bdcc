import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' }
  },
  {
    name: 'Customers',
    url: '/customers',
    iconComponent: { name: 'cil-people' }
  },
  {
    name: 'Bank Accounts',
    url: '/accounts',
    iconComponent: { name: 'cil-notes' }
  },
];

export const customerDashboard: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' }
  }
];
