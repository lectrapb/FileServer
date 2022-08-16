import { Injectable } from '@angular/core';
import { catchError, delay, map } from "rxjs/operators"; 
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import Swal from 'sweetalert2'

import { FileStorage } from '../model/file-storage';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { Filesave } from '../model/filesave';

@Injectable({
  providedIn: 'root'
})
export class FileService {


  constructor(private http: HttpClient) { }

  getFiles(): Observable<FileStorage[]>{
     
       const url = `${environment.base_url}/listcontent`;
       return this.http.get(url).pipe(
           delay(500),
           map(response => response as FileStorage[])
       );
  }

  deleteFile(file: FileStorage){
    const url = `${environment.base_url}/delete?namefile=${file.name}`;
    return this.http.delete(url);   
  }

  createFile(fileData: File): Observable<HttpEvent<{}>>{
     const url = `${environment.base_url}/upload`;
     const formData  = new FormData();
     formData.append('file', fileData );

     const req = new HttpRequest('POST', url, formData, {
       reportProgress: true
     });
     return this.http.request(req);
     /*
     return this.http.post(url, formData).pipe(
        map( (response: any) => response as Filesave),
        catchError(e =>{
           console.log(e);
           return throwError(e);           
        })
     );
     */
  }
}
