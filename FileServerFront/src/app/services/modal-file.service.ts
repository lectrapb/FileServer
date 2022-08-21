import { Injectable, EventEmitter  } from '@angular/core';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ModalFileService {

  private _hideModal: boolean = true;

  public newFile: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  get hideModal(){
     return this._hideModal;
  }

  openModal(): void{
     this._hideModal = false;
  }

  closeModal(): void{
    this._hideModal = true;
  }
}
