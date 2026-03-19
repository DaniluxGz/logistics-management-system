import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaBodegasComponent } from './lista-bodegas/lista-bodegas.component';

const routes: Routes = [
  { path: '', component: ListaBodegasComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BodegasRoutingModule { }