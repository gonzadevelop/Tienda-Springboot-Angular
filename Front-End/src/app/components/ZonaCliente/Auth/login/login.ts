import {Component, input, InputSignal, OnInit, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  public email:InputSignal<String> =input<String>('');
  protected mostrarPassword = signal<boolean>(false);

  protected loginForm:FormGroup = new FormGroup(
    {
      password: new FormControl('')
    }
  );


  ngOnInit(): void {
    console.log('componente de login cargado...');
    console.log('valor del email recibido como input:', this.email());
  }

  togglePasswordVisibility(): void {
    this.mostrarPassword.set(!this.mostrarPassword());
  }

  login(): void {
      console.log('Iniciando sesión con:', this.email(), this.loginForm.value.password);
      // Aquí irá la lógica de login con el servicio
    }
}
