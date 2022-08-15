import { Component, OnInit } from '@angular/core';

import { FileService } from '../../services/file.service';
import { FileStorage } from '../../model/file-storage';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit {

  listFiles: FileStorage[] = []; 

  constructor(private fileService: FileService) { }

  ngOnInit(): void {
    this.listFiles = this.fileService.getFiles();
  }

}
