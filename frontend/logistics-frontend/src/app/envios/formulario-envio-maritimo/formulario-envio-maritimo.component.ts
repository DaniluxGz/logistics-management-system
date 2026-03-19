import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvioService } from '../../shared/services/envio.service';
import { ClienteService } from '../../shared/services/cliente.service';
import { Cliente } from '../../shared/models/cliente.model';
import { Puerto } from '../../shared/models/envio.model';

@Component({
  selector: 'app-formulario-envio-maritimo',
  templateUrl: './formulario-envio-maritimo.component.html',
  styleUrls: ['./formulario-envio-maritimo.component.scss']
})
export class FormularioEnvioMaritimoComponent implements OnInit {

  @Output() envioCreado = new EventEmitter<void>();

  formulario: FormGroup;
  clientes: Cliente[] = [];
  puertos: Puerto[] = [];
  error: string = '';
  cargando: boolean = false;

  tiposProducto = ['ELECTRONICO', 'ROPA', 'ALIMENTOS', 'MAQUINARIA', 'QUIMICOS', 'OTROS'];

  constructor(
    private fb: FormBuilder,
    private envioService: EnvioService,
    private clienteService: ClienteService
  ) {
    this.formulario = this.fb.group({
      numeroGuia: ['', [Validators.required, Validators.pattern('^[A-Z0-9-]+$')]],
      tipoProducto: ['', Validators.required],
      cantidad: ['', [Validators.required, Validators.min(1)]],
      fechaRegistro: ['', Validators.required],
      fechaEntrega: ['', Validators.required],
      precioOriginal: ['', [Validators.required, Validators.min(0)]],
      clienteId: ['', Validators.required],
      numeroFlota: ['', Validators.required],
      puertoId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.clienteService.obtenerTodos().subscribe(data => this.clientes = data);
    this.envioService.obtenerPuertos().subscribe(data => this.puertos = data);
  }

  // Crear envio maritimo
  crear(): void {
    if (this.formulario.invalid) return;
    this.cargando = true;

    const envio = {
      ...this.formulario.value,
      cliente: { id: this.formulario.value.clienteId },
      puerto: { id: this.formulario.value.puertoId },
      precioFinal: this.formulario.value.precioOriginal
    };

    delete envio.clienteId;
    delete envio.puertoId;

    this.envioService.crearMaritimo(envio).subscribe({
      next: () => {
        this.envioCreado.emit();
        this.cargando = false;
      },
      error: () => {
        this.error = 'Error al crear el envío. Verifique los datos.';
        this.cargando = false;
      }
    });
  }
}