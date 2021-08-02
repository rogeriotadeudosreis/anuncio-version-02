import { catchError, map, retry } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse } from '@Angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { empty, Observable, throwError } from 'rxjs';
import { User } from './user.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseUrl = 'http://localhost:8080/anuncio02/user';

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMensagem(msg: string): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
    });
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user)
    .pipe(retry(1),
    catchError(this.handleError));
  }

  read(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  readById(id: string): Observable<User> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<User>(url);
  }

  update(user: User): Observable<User> {
    const url = `${this.baseUrl}/${user.id}`;
    return this.http.put<User>(url, user);
  }

  delete(id: String): Observable<User> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<User>(url);
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    this.showMensagem(errorMessage)
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
