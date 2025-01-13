import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { AdminHomeComponent } from './components/home/admin-home/admin-home.component';
import { CustomerHomeComponent } from './components/home/customer-home/customer-home.component';
import { DriverHomeComponent } from './components/home/driver-home/driver-home.component';
import { roleGuard } from './services/role.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'home/admin',
    component: AdminHomeComponent,
    canActivate: [roleGuard],
    data: { role: 'Admin' },
  },
  {
    path: 'home/customer',
    component: CustomerHomeComponent,
    canActivate: [roleGuard],
    data: { role: 'Customer' },
  },
  {
    path: 'home/driver',
    component: DriverHomeComponent,
    canActivate: [roleGuard],
    data: { role: 'Driver' },
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];
