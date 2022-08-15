import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PagesRoutingModule } from './pages-routing.module';
import { ComponentModule } from '../components/component.module';

import { PagesComponent } from './pages.component';
import { HomeComponent } from './home/home.component';



@NgModule({
  declarations: [
    PagesComponent,
    HomeComponent
  ],
  exports:[
    PagesComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    RouterModule,
    ComponentModule
  ]
})
export class PagesModule { }
