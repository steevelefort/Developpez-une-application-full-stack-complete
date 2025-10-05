import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

/**
 * Add JWT token to HTTP requests.
 * Skip login and register requests.
 */
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = inject(AuthService).token();

  const exclusions = ['/api/auth/login', '/api/auth/register'];
  if (exclusions.some(ex => req.url.includes(ex)) || !token) return next(req);

  return next(req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }));
};
