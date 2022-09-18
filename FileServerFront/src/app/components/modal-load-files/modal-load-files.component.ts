import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { HttpEventType } from '@angular/common/http';


import { ModalFileService } from '../../services/modal-file.service';
import { FileService } from '../../services/file.service';
import { FileStorage } from '../../model/file-storage';
import { environment } from '../../../environments/environment.prod';

@Component({
  selector: 'app-modal-load-files',
  templateUrl: './modal-load-files.component.html',
  styleUrls: ['./modal-load-files.component.css']
})
export class ModalLoadFilesComponent implements OnInit {

  public fileToUpload!: File;
  public progress: number = 0;


  constructor(public modalFileService: ModalFileService, 
              private fileService: FileService) { }

  ngOnInit(): void {
    this.progress = 0 
  }
  
  chooseFile(file: File){
    this.progress = 0;
    this.fileToUpload = file;
    if(!this.fileToUpload){    
      return;
    }
    
  }

  closeModal(): void{
    this.progress = 0;
    this.modalFileService.closeModal();
  }

  uploadImage(){
     this.fileService.createFile(this.fileToUpload)
     .subscribe(event =>{
          // let fileSub: any;
          // if(response){
          //       fileSub = response;
          // }  
          // this.modalFileService.newFile.emit(fileSub);
          // Swal.fire('Upload file sucess', `The file ${response.name} had been upload !`, 'success')
          
          if(event.type === HttpEventType.UploadProgress){
             let total = event.total == null ? 1: event.total;   
             this.progress = Math.round(((event.loaded/ total)*100)-2);

          } else if(event.type === HttpEventType.Response){
             
             let response: any = event.body;
             const d = new Date();
             const location: string =  `${environment.base_url}/download?filename=${response.name}`;
             const fileStorage: FileStorage = 
                                new FileStorage(response.id, response.name,
                                                response.type, d.toString() , location);
             this.modalFileService.newFile.emit(fileStorage); 
             this.progress = 100;
             Swal.fire('Upload file sucess', `The file ${response.name} had been upload !`, 'success')
          }
          //this.modalFileService.closeModal();
        });
  }




}
