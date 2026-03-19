import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { BodegasRoutingModule } from './bodegas-routing.module';
import { ListaBodegasComponent } from './lista-bodegas/lista-bodegas.component';

@NgModule({
  declarations: [ListaBodegasComponent],
  imports: [CommonModule, ReactiveFormsModule, BodegasRoutingModule]
})
export class BodegasModule { }