import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import ILoginRequest from '../model/ILoginRequest';
import ILoginResponse from '../model/ILoginResponse';
import IRegisterRequest from '../model/IRegisterRequest';
import ITokenRequest from '../model/ITokenRequest';
import IUsernameResponse from '../model/IUsernameResponse';
import ICategoriaResponse from '../model/ICategoriaResponse';

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

  public register( rquestBody: IRegisterRequest ):Observable<HttpResponse<void>> {
    return this.http
      .post<void>(
        this.baseUrl + 'auth/register',
        rquestBody,
        {
          observe: 'response'
        }
      );
  }

  public getUsernameFromJWT( token:ITokenRequest ):Observable<HttpResponse<IUsernameResponse>> {
    return this.http
      .post<IUsernameResponse>(
        this.baseUrl + 'api/usuario/username-from-jwt',
        token,
        {
          observe: 'response'
        }
      );
  }

  public getCategorias(nombreCat:string | null): Observable<HttpResponse<ICategoriaResponse[]>> {
    if (nombreCat===null) {
      return this.http
        .get<ICategoriaResponse[]>(
          this.baseUrl + 'api/categorias/padres-hijas',
          {
            observe: 'response'
          }
        );
    } else {
      return this.http
        .get<ICategoriaResponse[]>(
          this.baseUrl + `api/categorias/padres-hijas?nombre=${nombreCat}`,
          {
            observe: 'response'
          }
        )
    }
  }
}
