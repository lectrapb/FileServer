import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { HomeComponent } from './home/home.component';
import { PagesComponent } from './pages.component';

export const routes: Routes = [
  { path:'test', 
    component: PagesComponent,
    children: [
      {path: 'home', component: HomeComponent}
    ]
 }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]

})
export class PagesRoutingModule { }
