import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering'

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { ModalLoadFilesComponent } from './modal-load-files/modal-load-files.component';
import { VideoPlayerComponent } from './video-player/video-player.component';




@NgModule({
  declarations: [
    NavbarComponent, 
    FooterComponent, 
    ModalLoadFilesComponent, 
    VideoPlayerComponent
                ],
  exports:[
    NavbarComponent, 
    FooterComponent, 
    ModalLoadFilesComponent,
    VideoPlayerComponent, 
    
  ],
  imports: [
    CommonModule, 
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ]
})
export class ComponentModule { }
