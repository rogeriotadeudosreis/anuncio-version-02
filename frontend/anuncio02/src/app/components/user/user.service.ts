import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private snackBar: MatSnackBar) { }

  showMensagem(msg: string): void{
    this.snackBar.open(msg,'X', {
      duration:3000,
      horizontalPosition: 'right',
      verticalPosition: 'top'
    })
  }
}
