import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaClientesComponent } from './lista-clientes/lista-clientes.component';
import { FormularioClienteComponent } from './formulario-cliente/formulario-cliente.component';

const routes: Routes = [
  { path: '', component: ListaClientesComponent },
  { path: 'nuevo', component: FormularioClienteComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }