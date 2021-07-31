import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';

@Component({
  selector: 'app-user-read',
  templateUrl: './user-read.component.html',
  styleUrls: ['./user-read.component.css']
})
export class UserReadComponent implements OnInit {

  users: User[]

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.read().subscribe((resposta) => {
      this.users = resposta
      console.log(this.users)
    })
  }

}
