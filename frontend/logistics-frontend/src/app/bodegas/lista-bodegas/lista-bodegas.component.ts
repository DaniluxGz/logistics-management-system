import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvioService } from '../../shared/services/envio.service';
import { Bodega } from '../../shared/models/envio.model';

@Component({
  selector: 'app-lista-bodegas',
  templateUrl: './lista-bodegas.component.html',
  styleUrls: ['./lista-bodegas.component.scss']
})
export class ListaBodegasComponent implements OnInit {

  bodegas: Bodega[] = [];
  mostrarFormulario = false;
  formulario: FormGroup;
  cargando = false;

  constructor(
    private envioService: EnvioService,
    private fb: FormBuilder
  ) {
    this.formulario = this.fb.group({
      nombre: ['', Validators.required],
      ubicacion: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cargarBodegas();
  }

  cargarBodegas(): void {
    this.envioService.obtenerBodegas().subscribe(data => this.bodegas = data);
  }

  crear(): void {
    if (this.formulario.invalid) return;
    this.envioService.crearBodega(this.formulario.value).subscribe({
      next: () => {
        this.mostrarFormulario = false;
        this.formulario.reset();
        this.cargarBodegas();
      }
    });
  }
}