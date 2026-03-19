import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClienteService } from '../../shared/services/cliente.service';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.scss']
})
export class FormularioClienteComponent {

  @Output() clienteCreado = new EventEmitter<void>();

  formulario: FormGroup;
  error: string = '';
  cargando: boolean = false;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService
  ) {
    this.formulario = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });
  }

  crear(): void {
    if (this.formulario.invalid) return;
    this.cargando = true;

    this.clienteService.crear(this.formulario.value).subscribe({
      next: () => {
        this.clienteCreado.emit();
        this.cargando = false;
      },
      error: () => {
        this.error = 'Error al crear el cliente.';
        this.cargando = false;
      }
    });
  }
}