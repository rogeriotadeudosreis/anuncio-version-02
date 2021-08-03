import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnnoucementReadComponent } from './components/annoucement/annoucement-read/annoucement-read.component';
import { SigninComponent } from './components/login/signin/signin.component';
import { ProfileCreateComponent } from './components/profile/profile-create/profile-create.component';
import { UserCreateComponent } from './components/user/user-create/user-create.component';
import { UserDeleteComponent } from './components/user/user-delete/user-delete.component';
import { UserUpdateComponent } from './components/user/user-update/user-update.component';
import { HomeComponent } from './views/home/home.component';
import { UserCrudComponent } from './views/user-crud/user-crud.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'users',
    component: UserCrudComponent,
  },
  {
    path: 'users/create',
    component: UserCreateComponent,
  },
  {
    path: 'profiles/create',
    component: ProfileCreateComponent,
  },
  {
    path: 'user/update/:id',
    component: UserUpdateComponent,
  },
  {
    path: 'user/delete/:id',
    component: UserDeleteComponent,
  },
  {
    path: 'login',
    component: SigninComponent,
  },
  {
    path: 'anuncio',
    component: AnnoucementReadComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
