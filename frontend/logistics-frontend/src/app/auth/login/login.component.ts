import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  formulario: FormGroup;
  error: string = '';
  cargando: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.formulario = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // Iniciar sesion
  iniciarSesion(): void {
    if (this.formulario.invalid) return;
    this.cargando = true;
    this.error = '';

    this.authService.login(this.formulario.value).subscribe({
      next: () => this.router.navigate(['/envios']),
      error: () => {
        this.error = 'Credenciales incorrectas. Intente de nuevo.';
        this.cargando = false;
      }
    });
  }
}