import { map } from 'rxjs/operators';
import { HttpClient } from '@Angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = "http://localhost:8080/anuncio02/user"

  constructor(
    private snackBar: MatSnackBar,
    private http: HttpClient) { }

  showMensagem(msg: string): void{
    this.snackBar.open(msg,'X', {
      duration:3000,
      horizontalPosition: 'right',
      verticalPosition: 'top'
    })
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user)
  }

  read(): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl)
  }

}
