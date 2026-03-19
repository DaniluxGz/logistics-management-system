import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnvioService } from '../../shared/services/envio.service';
import { Puerto } from '../../shared/models/envio.model';

@Component({
  selector: 'app-lista-puertos',
  templateUrl: './lista-puertos.component.html',
  styleUrl: './lista-puertos.component.scss'
})
export class ListaPuertosComponent implements OnInit {

  puertos: Puerto[] = [];
  mostrarFormulario = false;
  formulario: FormGroup;

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
    this.cargarPuertos();
  }

  cargarPuertos(): void {
    this.envioService.obtenerPuertos().subscribe(data => this.puertos = data);
  }

  crear(): void {
    if (this.formulario.invalid) return;
    this.envioService.crearPuerto(this.formulario.value).subscribe({
      next: () => {
        this.mostrarFormulario = false;
        this.formulario.reset();
        this.cargarPuertos();
      }
    });
  }
}