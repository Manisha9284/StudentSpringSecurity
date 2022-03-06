import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http:HttpClient) { }

  //path:string="/assets/data/stud.json";
  // The request mapping address ofevery controller should start with v1
  serverPath:string="http://localhost:8088/v1/admin/";

  //  All methods in service accept object of Options class sent from every entity form like StudComponent.ts
  //  This object is further sent to all methods of http client like get, post, put, delete etc  
  getData(url, options: Options):Observable<any>
  {
    alert("Inside getData()");
    options = this.setHeaders(options);
    return this.http.get(this.serverPath + url, options);
  } 

  insertData(url, obj, options: Options):Observable<any>
  {
    options = this.setHeaders(options);
    return this.http.post(this.serverPath + url, obj, options);
  }   

  updateData(url, obj, options: Options):Observable<any>
  {
    options = this.setHeaders(options);
    return this.http.put(this.serverPath + url, obj, options);
  }
  
  deleteData(url, options: Options):Observable<any>
  {
    options = this.setHeaders(options);
    return this.http.delete(this.serverPath + url, options);
  }

  private setHeaders(options) {
    if (!options)
      options = new Options();
    if (sessionStorage.getItem('access-token')) {
      options.headers['access-token'] = sessionStorage.getItem('access-token');
    }
    return options;
  }

}
// This class is required to send request parameters, headers to API tool
export class Options {
  params: any;
  headers: any;
  observe: any;
  constructor() {
    this.params = {};
    this.headers = {};
    this.observe = 'body';
  }
}

