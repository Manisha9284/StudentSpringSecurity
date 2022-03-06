import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { DataService, Options } from '../service/data.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  public imagePath;
  imgURL: any;
  public message: string;

  constructor(private http:DataService) { }

  ngOnInit(): void {
    this.imgURL="../assets/mydata/unknown.jpg";
    //this.preview("");
  }

  files;

  preview(files) {
    this.files=files;

    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      alert("Only images are supported");
      this.imgURL=null;
      return;
    }
 
    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }

  upload(){
    alert("Inside upload");

    let data = new FormData();
          let fileItem = this.files[0];
          //console.log(fileItem.name);
          data.append('file', fileItem);
          data.append('fileSeq', 'seq');
          //data.append( 'dataType', this.uploadForm.controls.type.value);
          data.append( 'dataType', this.files[0].type.value);
          //this.uploadFile(data).subscribe(data => alert(data.message));
          alert(data);
          this.uploadFile(data).subscribe(data => alert("Hi" + data.message));
  }

  uploadFile(data: FormData): Observable<any> {
    let options:Options=new Options();
    alert("Helloooo  " + JSON.stringify(data));
    return this.http.insertData('students/uploadFile', data, options);
  }

  delete() {
    this.imgURL="../assets/mydata/unknown.jpg";
  }



}
