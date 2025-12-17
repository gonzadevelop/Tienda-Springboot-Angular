import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {ServApiSpring} from '../../../../services/ServApiSpring';
import {Subscription} from 'rxjs';
import {HttpResponse} from '@angular/common/http';
import IUsernameResponse from '../../../../model/IUsernameResponse';
import ITokenRequest from '../../../../model/ITokenRequest';
import {CamelCaseStringPipe} from '../../../../pipes/camel-case-string-pipe';

@Component({
  selector: 'app-hola-usuario',
  imports: [ CamelCaseStringPipe ],
  templateUrl: './hola-usuario.html',
  styleUrl: './hola-usuario.css',
})
export class HolaUsuario implements OnInit, OnDestroy{
  private servApiSpring:ServApiSpring = inject(ServApiSpring);
  private suscribeGetUsername:Subscription | null = null;

  username:string | null = null;


  ngOnInit():void {
    const jwt: string | null = localStorage.getItem('JWT');
    if (jwt) {
      const jwtRequest: ITokenRequest = { token: jwt };
      this.suscribeGetUsername = this.servApiSpring.getUsernameFromJWT(jwtRequest).subscribe({
        next: (response: HttpResponse<IUsernameResponse>): void => {
          if (response.status === 200) {
            console.log('Hola, ' + response.body!.username + '!');
            this.username = response.body!.username;
          }
        },
        error: (error): void => {
          console.error('Error al obtener el nombre de usuario desde el JWT:', error);
        }
      });
    }
  }

  ngOnDestroy():void {
    this.suscribeGetUsername?.unsubscribe();
  }
}
