import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvioService } from '../../shared/services/envio.service';
import { ClienteService } from '../../shared/services/cliente.service';
import { Cliente } from '../../shared/models/cliente.model';
import { Bodega } from '../../shared/models/envio.model';

@Component({
  selector: 'app-formulario-envio-terrestre',
  templateUrl: './formulario-envio-terrestre.component.html',
  styleUrls: ['./formulario-envio-terrestre.component.scss']
})
export class FormularioEnvioTerrestreComponent implements OnInit {

  @Output() envioCreado = new EventEmitter<void>();

  formulario: FormGroup;
  clientes: Cliente[] = [];
  bodegas: Bodega[] = [];
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
      placaVehiculo: ['', [Validators.required, Validators.pattern('^[A-Z]{3}-?\\d{3}$')]],
      bodegaId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.clienteService.obtenerTodos().subscribe(data => this.clientes = data);
    this.envioService.obtenerBodegas().subscribe(data => this.bodegas = data);
  }

  // Crear envio terrestre
  crear(): void {
    if (this.formulario.invalid) return;
    this.cargando = true;

    const envio = {
      ...this.formulario.value,
      cliente: { id: this.formulario.value.clienteId },
      bodega: { id: this.formulario.value.bodegaId },
      precioFinal: this.formulario.value.precioOriginal
    };

    delete envio.clienteId;
    delete envio.bodegaId;

    this.envioService.crearTerrestre(envio).subscribe({
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