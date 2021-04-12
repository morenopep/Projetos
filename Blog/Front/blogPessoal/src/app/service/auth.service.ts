import { UserLogin } from './../model/UserLogin';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/User';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  //O Observable mapeia o retorno do médoto.
  logar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://blogpessoal-devs-trainer.herokuapp.com/usuarios/logar', userLogin)
  }

  cadastrar(user: User): Observable<User>{
    return this.http.post<User>('https://blogpessoal-devs-trainer.herokuapp.com/usuarios/cadastrar', user)
  }

  getAllUsers(): Observable<User[]>{
    return this.http.get<User[]>('https://blogpessoal-devs-trainer.herokuapp.com/usuarios', this.token)
  }

  getByIdUser(id: number): Observable<User>{
    return this.http.get<User>('https://blogpessoal-devs-trainer.herokuapp.com/usuarios/${id}', this.token)
  }

  putUser(user: User) : Observable<User>{
    return this.http.put<User>('https://blogpessoal-devs-trainer.herokuapp.com/usuarios', user, this.token)
  }

  menu(){
    let ok = false

    if(environment.token != ''){
      ok = true
    }

    return ok
  }


}
