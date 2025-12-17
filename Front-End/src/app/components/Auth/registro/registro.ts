import {
  Component, inject,
  input,
  InputSignal,
  OnDestroy,
  OnInit,
  output,
  OutputEmitterRef,
  signal,
  WritableSignal
} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ServApiSpring} from '../../../services/ServApiSpring';
import ILoginRequest from '../../../model/ILoginRequest';
import IRegisterRequest from '../../../model/IRegisterRequest';
import { Subscription } from 'rxjs';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-registro',
  imports: [ReactiveFormsModule],
  templateUrl: './registro.html',
  styleUrl: './registro.css',
})
export class Registro implements OnInit, OnDestroy {
  private servApiSpring:ServApiSpring = inject( ServApiSpring );
  private suscripcionRegistro:Subscription | null = null;

  public email:InputSignal<string> = input<string>('');
  public atras:OutputEmitterRef<void> = output<void>();

  protected mostrarPassword:WritableSignal<boolean> = signal<boolean>(false);

  protected registroForm:FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$')]),
    nombre: new FormControl('', [Validators.required]),
    apellidos: new FormControl('', [Validators.required]),
    genero: new FormControl('', [Validators.required]),
    dni: new FormControl('', [Validators.pattern(/^[0-9]{8}[A-Za-z]$/)]),
    telefono: new FormControl('', [Validators.pattern(/^[0-9]{9}$/)])
  });



  registrar(): void {
    if (this.registroForm.valid) {
      const registroData:ILoginRequest = {
        email: this.email(),
        password: this.registroForm.value
      };
      console.log('Registrando usuario con:', registroData);
      const requestBody:IRegisterRequest =  {
        username: this.registroForm.value.username,
        password: this.registroForm.value.password,
        email: this.email(),
        nombre: this.registroForm.value.nombre,
        apellidos: this.registroForm.value.apellidos,
        genero: this.registroForm.value.genero,
        dni: this.registroForm.value.dni,
        telefonoContacto: this.registroForm.value.telefono
      };
      this.suscripcionRegistro = this.servApiSpring.register(requestBody).subscribe({
        next: (response:HttpResponse<void>) => {
          if (response.status === 200) {
            console.log('Registro exitoso. Por favor, revise su correo electrónico para activar su cuenta.');

          }
        },
        error: (error:any) => {
          console.error('Error durante el registro:', error);

        }
      });
    }
  }

  togglePasswordVisibility(): void {
    this.mostrarPassword.set(!this.mostrarPassword());
  }

  goBack(): void {
    this.atras.emit();
  }

  ngOnInit(): void {
    console.log('componente de registro cargado...');
    console.log('valor del email recibido como input:', this.email());
  }

  ngOnDestroy():void {
    this.suscripcionRegistro?.unsubscribe();
  }
}
