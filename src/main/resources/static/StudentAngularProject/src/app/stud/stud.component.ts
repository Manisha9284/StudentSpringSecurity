import { Component, OnInit } from '@angular/core';
import { Student } from '../pojo/Student';
import { DataService, Options } from '../service/data.service';

//import for poup
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

//import for data table
import { ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';


@Component({
  selector: 'app-stud',
  templateUrl: './stud.component.html',
  styleUrls: ['./stud.component.css']
})
export class StudComponent implements OnInit {

  public students=[ ]; 
  public studObj:Student=new Student();
  public flag:string;

  // ******************************************//
  // Following object is created to send access token through request header to API. 
  // ******************************************//
  options:Options=new Options();

  //For data table
  ELEMENT_DATA: Stud[]=null;
  displayedColumns: string[] = ['sno', 'sname', 'age', 'update', 'delete', 'img'];
  dataSource = new MatTableDataSource<Stud>(this.ELEMENT_DATA);
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  constructor(private dataService:DataService, private modalService:NgbModal) { }

  ngOnInit(): void {
    this.fillList();
  }

  fillList()
  {
    //this.dataService.getData("students").subscribe(data=>{this.students=data;});    
    //this.clearData();

    // ******************************************//
    // The options object is sent to every method of data service
    // ******************************************//
    this.dataService.getData("students", this.options).subscribe(data=>{this.ELEMENT_DATA=data; /*alert(JSON.stringify(this.ELEMENT_DATA));*/ this.dataSource = new MatTableDataSource<Stud>(this.ELEMENT_DATA); this.dataSource.paginator = this.paginator;}); 
    this.clearData();

  }

  clearData()
  {
    this.studObj.sno=null;
    this.studObj.sname=null;
    this.studObj.age=null;
  }

  save()
  {
    // ******************************************//
    // The options object is sent to every method of data service
    // ******************************************//
    if(this.flag=="Add")
        this.dataService.insertData("students", this.studObj, this.options).subscribe(data=>{this.fillList();});
    else
       {
         alert("Inside else");
        this.dataService.updateData("students", this.studObj, this.options).subscribe(data=>{this.fillList();});
       }
        
  }

  update(sno)
  {
    //alert(sno);
    // ******************************************//
    // The options object is sent to every method of data service
    // ******************************************//
    this.flag="Update";
    this.dataService.getData("students/" + sno, this.options).subscribe(data=>{this.studObj=data;});         
  }  

  edit()
  {
    
  }

  delete(sno)
  {
     let ans=confirm("R u sure u wanna to delete this record?"); 

     if(ans==false) return; 

     // ******************************************//
    // The options object is sent to every method of data service
    // ******************************************//
     this.dataService.deleteData("students/" + sno, this.options).subscribe(data=>{this.fillList();});
  }

  add()
  {
    this.flag="Add";
  }


  //Code for modal starts here
// *************************
openPopUp(content) {
  this.modalService.open(content, { size: 'lg' });
}

private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}

// *************************
//Code for modal ends here



}
export class Stud {
  sno:number;
  sname: string;
  age:number;
  update:string;
  delete:string;
  img:string;
}
