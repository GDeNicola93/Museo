import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrarVentaEntradaComponent } from './componentes/entradas/registrar-venta-entrada/registrar-venta-entrada.component';
import { HomeComponent } from './componentes/home/home.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},

  {path: 'entradas/registrar-venta-entrada', component: RegistrarVentaEntradaComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
