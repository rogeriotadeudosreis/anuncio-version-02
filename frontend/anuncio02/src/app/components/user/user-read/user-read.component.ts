import { Router } from '@angular/router';
import { UserService } from './../user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../user.model';

@Component({
  selector: 'app-user-read',
  templateUrl: './user-read.component.html',
  styleUrls: ['./user-read.component.css']
})
export class UserReadComponent implements OnInit {
  
  users: User[];
  displayedColumns = ['id','name','email','dataRegister','dataRegisterUpdate','active','profiles'];
  
  constructor(
    private userService: UserService,
    private router: Router) { }
  
  ngOnInit(): void {
    this.userService.read().subscribe((data : any) => {
      this.users = data.content
      
      console.log("Lista de usuários",this.users)
    })
  } 

  navigateToUserCreate(): void {
    this.router.navigate(['/users/create'])
  }

}
