import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user.model';
import { Profile } from '../../profile/profile.model';
import { pipe, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css'],
})
export class UserCreateComponent implements OnInit {
  user: User = {
    name: 'usuário teste angular',
    email: 'teste@gmail.com',
    password: '321654',
  };

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  createUser(): void {
    this.userService.create(this.user).subscribe(() => {
      this.userService.showMensagem('Usuário criado com sucesso!');
      this.router.navigate(['/users']);
    });
  }

  cancel(): void {
    this.router.navigate(['/users']);
  }
}
