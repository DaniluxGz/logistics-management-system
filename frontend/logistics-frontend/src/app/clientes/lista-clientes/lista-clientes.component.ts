import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClienteService } from '../../shared/services/cliente.service';
import { Cliente } from '../../shared/models/cliente.model';

@Component({
  selector: 'app-lista-clientes',
  templateUrl: './lista-clientes.component.html',
  styleUrls: ['./lista-clientes.component.scss']
})
export class ListaClientesComponent implements OnInit {

  clientes: Cliente[] = [];
  mostrarFormulario = false;
  cargando = false;

  constructor(
    private clienteService: ClienteService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarClientes();
  }

  cargarClientes(): void {
    this.cargando = true;
    this.clienteService.obtenerTodos().subscribe({
      next: (data) => { this.clientes = data; this.cargando = false; },
      error: () => { this.cargando = false; }
    });
  }

  eliminar(id: number): void {
    if (confirm('¿Está seguro de eliminar este cliente?')) {
      this.clienteService.eliminar(id).subscribe({
        next: () => this.cargarClientes()
      });
    }
  }

  onClienteCreado(): void {
    this.mostrarFormulario = false;
    this.cargarClientes();
  }
}