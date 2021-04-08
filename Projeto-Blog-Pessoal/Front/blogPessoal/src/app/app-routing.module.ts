import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { DeleteTemaComponent } from './delete/delete-tema/delete-tema.component';
import { TemaEditComponent } from './edit/tema-edit/tema-edit.component';
import { EntrarComponent } from './entrar/entrar.component';
import { InicioComponent } from './inicio/inicio.component';
import { TemaComponent } from './tema/tema.component';

const routes: Routes = [
  {path:'entrar',component:EntrarComponent},
  {path:'cadastrar',component:CadastrarComponent},
  {path:'',redirectTo:'entrar',pathMatch:'full'},
  {path: 'inicio', component: InicioComponent },
  {path: 'tema', component: TemaComponent},
  {path: 'tema-edit/:id',component : TemaEditComponent},
  {path: 'delete-tema/:id', component : DeleteTemaComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
