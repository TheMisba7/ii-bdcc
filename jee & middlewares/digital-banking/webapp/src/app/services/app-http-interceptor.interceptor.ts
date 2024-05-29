import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpInterceptorFn,
  HttpRequest
} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {catchError, finalize, Observable, throwError} from "rxjs";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  constructor(private authService :AuthService, private route: Router) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.authService.isAuthenticated) return next.handle(req)

    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + this.authService.token)
    });
    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Navigate to the login page
          localStorage.clear()
          this.route.navigate(['/login']);
        }
        return throwError(error);
      }))
  }

}
