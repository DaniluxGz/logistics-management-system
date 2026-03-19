import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { PuertosRoutingModule } from './puertos-routing.module';
import { ListaPuertosComponent } from './lista-puertos/lista-puertos.component';

@NgModule({
  declarations: [ListaPuertosComponent],
  imports: [CommonModule, ReactiveFormsModule, PuertosRoutingModule]
})
export class PuertosModule { }