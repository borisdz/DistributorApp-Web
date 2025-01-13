import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from 'express';
import { AuthService } from './auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const expectedRole = route.data?.['role'];
  const userRole = authService.getUserRole();

  if (userRole === expectedRole) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};

// function getUserRole(authService: AuthService): string | null {
//   const token = authService.getToken();
//   if(token){
//     try{
//       const payload = JSON.parse(atob(token.split('.')[1]));
//       return payload.role;
//     }catch(error){
//       console.error('Invalid token format:',error);
//       return null;
//     }
//   }
// }
