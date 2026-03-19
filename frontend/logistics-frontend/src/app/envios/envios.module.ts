import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { EnviosRoutingModule } from './envios-routing.module';
import { ListaEnviosComponent } from './lista-envios/lista-envios.component';
import { FormularioEnvioTerrestreComponent } from './formulario-envio-terrestre/formulario-envio-terrestre.component';
import { FormularioEnvioMaritimoComponent } from './formulario-envio-maritimo/formulario-envio-maritimo.component';

@NgModule({
  declarations: [
    ListaEnviosComponent,
    FormularioEnvioTerrestreComponent,
    FormularioEnvioMaritimoComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    EnviosRoutingModule
  ]
})
export class EnviosModule { }