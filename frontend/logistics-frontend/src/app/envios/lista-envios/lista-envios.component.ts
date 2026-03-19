import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EnvioService } from '../../shared/services/envio.service';
import { AuthService } from '../../shared/services/auth.service';
import { EnvioTerrestre, EnvioMaritimo } from '../../shared/models/envio.model';

@Component({
  selector: 'app-lista-envios',
  templateUrl: './lista-envios.component.html',
  styleUrls: ['./lista-envios.component.scss']
})
export class ListaEnviosComponent implements OnInit {

  enviosTerrestres: EnvioTerrestre[] = [];
  enviosMaritimos: EnvioMaritimo[] = [];
  vistaActiva: 'terrestres' | 'maritimos' = 'terrestres';
  mostrarFormularioTerrestre = false;
  mostrarFormularioMaritimo = false;
  cargando = false;

  constructor(
    private envioService: EnvioService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarEnvios();
  }

  // Cargar todos los envios
  cargarEnvios(): void {
    this.cargando = true;
    this.envioService.obtenerTerrestres().subscribe({
      next: (data) => { this.enviosTerrestres = data; this.cargando = false; },
      error: () => { this.cargando = false; }
    });
    this.envioService.obtenerMaritimos().subscribe({
      next: (data) => { this.enviosMaritimos = data; },
    });
  }

  // Cerrar sesion
  cerrarSesion(): void {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }

  // Eliminar envio terrestre
  eliminarTerrestre(id: number): void {
    if (confirm('¿Está seguro de eliminar este envío?')) {
      this.envioService.eliminarTerrestre(id).subscribe({
        next: () => this.cargarEnvios()
      });
    }
  }

  // Eliminar envio maritimo
  eliminarMaritimo(id: number): void {
    if (confirm('¿Está seguro de eliminar este envío?')) {
      this.envioService.eliminarMaritimo(id).subscribe({
        next: () => this.cargarEnvios()
      });
    }
  }

  // Callback cuando se crea un envio
  onEnvioCreado(): void {
    this.mostrarFormularioTerrestre = false;
    this.mostrarFormularioMaritimo = false;
    this.cargarEnvios();
  }
}