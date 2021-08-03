import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username:['', Validators.required],
      password: ['', Validators.required]
    })
  }

  cancel():void {
    this.router.navigate([''])
  }

}
