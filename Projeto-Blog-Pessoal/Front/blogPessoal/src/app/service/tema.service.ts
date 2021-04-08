import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';

@Injectable({
  providedIn: 'root'
})
export class TemaService {
  token = {
    headers : new HttpHeaders().set('Authorization',environment.token)
  }

  constructor(private http:HttpClient ) { }

  getAllTema(): Observable<Tema[]>{ 
    return this.http.get<Tema[]>('http://localhost:8080/tema',this.token)
  }
  postTema(tema: Tema): Observable<Tema>{
    return this.http.post<Tema>('http://localhost:8080/tema',tema,this.token)
  }

  //criando os metodos delete e put

  putTema(tema: Tema): Observable<Tema>{
    return this.http.put<Tema>('http://localhost:8080/tema', tema, this.token)
  }

  deleteTema(id : number) {
    return this.http.delete(`http://localhost:8080/tema/${id}`, this.token)
  }
  
  //metodos getById para buscar no banco pelo Id

  getByIdTema(id: number):  Observable<Tema>{
    return this.http.get<Tema>(`http://localhost:8080/tema/${id}`, this.token)
  }


}
