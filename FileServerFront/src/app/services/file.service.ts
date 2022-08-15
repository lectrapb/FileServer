import { Injectable } from '@angular/core';
import { FileStorage } from '../model/file-storage';

import { FILES } from '../model/listfiles.json';


@Injectable({
  providedIn: 'root'
})
export class FileService {



  constructor() { }

  getFiles(): FileStorage[]{

       return FILES;
  }
}
