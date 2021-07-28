import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
    component: UserCrudComponent,
  },
  {
    path: "user/create",
    component: UserCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
