import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { ModalLoadFilesComponent } from './modal-load-files/modal-load-files.component';



@NgModule({
  declarations: [
    NavbarComponent, 
    FooterComponent, 
    ModalLoadFilesComponent
                ],
  exports:[
    NavbarComponent, 
    FooterComponent, 
    ModalLoadFilesComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ComponentModule { }
