import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './shared/guards/auth.guard';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'clientes',
    loadChildren: () => import('./clientes/clientes.module').then(m => m.ClientesModule),
    canActivate: [authGuard]
  },
  {
    path: 'envios',
    loadChildren: () => import('./envios/envios.module').then(m => m.EnviosModule),
    canActivate: [authGuard]
  }, {
    path: 'bodegas',
    loadChildren: () => import('./bodegas/bodegas.module').then(m => m.BodegasModule),
    canActivate: [authGuard]
  },
  {
    path: 'puertos',
    loadChildren: () => import('./puertos/puertos.module').then(m => m.PuertosModule),
    canActivate: [authGuard]
  },
  {
    path: '',
    redirectTo: 'envios',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }