import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { stdout } from 'process';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { StudComponent } from './stud/stud.component';
import { UploadComponent } from './upload/upload.component';

const routes: Routes = [
   {path:"stud", component:StudComponent},
   {path:"login", component:LoginComponent},
   {path:"home", component:HomeComponent},
   {path:"upload", component:UploadComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
