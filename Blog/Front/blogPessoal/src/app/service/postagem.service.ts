import { Postagem } from './../model/Postagem';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment.prod';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllPostagens(): Observable<Postagem[]> {
    return this.http.get<Postagem[]>('https://blogpessoal-devs-trainer.herokuapp.com/postagens', this.token)
  }

  getByIdPostagem(id: number): Observable<Postagem> {
    return this.http.get<Postagem>(`https://blogpessoal-devs-trainer.herokuapp.com/postagens/${id}`, this.token)
  }

  getByNomePostagem(titulo: string): Observable<Postagem[]> {
    return this.http.get<Postagem[]>(`https://blogpessoal-devs-trainer.herokuapp.com/postagens/${titulo}`, this.token)
  }

  postPostagem(postagem: Postagem): Observable<Postagem>{
    return this.http.post<Postagem>('https://blogpessoal-devs-trainer.herokuapp.com/postagens', postagem, this.token)
  }

  putPostagem(postagem: Postagem): Observable<Postagem>{
    return this.http.put<Postagem>('https://blogpessoal-devs-trainer.herokuapp.com/postagens', postagem, this.token)
  }

  deletePostagem(id: number){
    return this.http.delete(`https://blogpessoal-devs-trainer.herokuapp.com/postagens/${id}`, this.token)
  }

}
