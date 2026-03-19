import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaEnviosComponent } from './lista-envios/lista-envios.component';

const routes: Routes = [
  { path: '', component: ListaEnviosComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnviosRoutingModule { }