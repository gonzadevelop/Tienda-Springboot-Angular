import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import ILoginRequest from '../model/ILoginRequest';
import ILoginResponse from '../model/ILoginResponse';

@Injectable({
  providedIn: 'root',
})
export class ServApiSpring {
  private http:HttpClient = inject(HttpClient);
  private baseUrl: string = 'http://localhost:8080/';

  public checkResitro(email: string):Observable<HttpResponse<boolean>> {
    const params = new HttpParams().set('email', email);
    return this.http
      .post<boolean>(
        this.baseUrl + 'auth/check-email',
        null,
        {
          params,
          observe: 'response'
        }
      );
  }
  public login( requestBody:ILoginRequest ):Observable<HttpResponse<ILoginResponse>> {
    return this.http
      .post<ILoginResponse>(
        this.baseUrl + 'auth/login',
        requestBody,
        {
          observe: 'response'
        }
      );
  }
}
