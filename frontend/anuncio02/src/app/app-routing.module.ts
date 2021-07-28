import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileCreateComponent } from './components/profile/profile-create/profile-create.component';
import { UserCreateComponent } from './components/user/user-create/user-create.component';
import { HomeComponent } from './views/home/home.component';
import { UserCrudComponent } from './views/user-crud/user-crud.component';

const routes: Routes = [
  {
    path: "", 
    component: HomeComponent
  },
  {
    path: "users",
    component: UserCrudComponent
  },
  {
    path: "users/create",
    component: UserCreateComponent
  },
  {
    path: "profiles/create",
    component: ProfileCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
