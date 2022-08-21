import { Component, OnDestroy, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Subscription } from "rxjs";
import { delay } from 'rxjs/operators';

import { FileService } from '../../services/file.service';
import { FileStorage } from '../../model/file-storage';
import { ModalFileService } from '../../services/modal-file.service';
import { environment } from "../../../environments/environment";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit, OnDestroy {

  public listFiles: FileStorage[] = []; 
  public loading: boolean = true;
  public fileSubs!: Subscription;
  public urlbase: string = environment.base_url;

  constructor(private fileService: FileService, 
              public modalFile: ModalFileService) { }

  ngOnDestroy(): void {
    this.fileSubs.unsubscribe();
  }

  ngOnInit(): void {
      this.loadFiles(); 
      this.fileSubs = this.modalFile.newFile
      .pipe(
        delay(100)
      )
      .subscribe(fileSave =>{
         console.log('file upload =>', fileSave);
         this.listFiles.push(fileSave);
      });

     
       
  }

  loadFiles():void{
    this.loading = true;
    this.fileService.getFiles().subscribe( 
      files => {
        this.listFiles = files;
        
        this.listFiles = this.listFiles.map(fileInitial =>{
          let url = `${environment.base_url}/download?filename=${fileInitial.name}`;  
          fileInitial.content = url; 
          return fileInitial;
        });

        this.loading = false;
      }
   );
  }

  deleteFile(file: FileStorage){
    
    Swal.fire({
      title: 'Are you sure?',
      text: "Are you sure to delete this file",
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          
          this.fileService.deleteFile(file)
          .subscribe(resp => {
                 
            Swal.fire('Deleted!',
              'Your file has been deleted.',
              'success'
              );
              this.loadFiles();
          });
        
      }
    });
  }

}
