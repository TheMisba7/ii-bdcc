import {HttpEvent, HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {finalize, Observable} from 'rxjs';
import {Injectable} from "@angular/core";
import {SpinnerService} from "./spinner.service";

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  constructor(private spinner :SpinnerService) {}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinner.show()
      return next.handle(req).pipe(
        finalize(() => {
          this.spinner.hide()
        })
      );
    }

}
