import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
  }
  
  createUser(): void {
    this.userService.showMensagem('Usuário cadastrado com sucesso!')
  }

  cancel(): void {
    this.router.navigate(['/users'])
  }

}
