import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaPuertosComponent } from './lista-puertos/lista-puertos.component';

const routes: Routes = [
  { path: '', component: ListaPuertosComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PuertosRoutingModule { }