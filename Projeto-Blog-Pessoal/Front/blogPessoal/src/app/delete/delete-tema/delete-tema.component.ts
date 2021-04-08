import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from 'src/app/model/Tema';
import { TemaService } from 'src/app/service/tema.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-delete-tema',
  templateUrl: './delete-tema.component.html',
  styleUrls: ['./delete-tema.component.css']
})
export class DeleteTemaComponent implements OnInit {
  tema: Tema = new Tema()
  idTema: number
  constructor(
    private temaService : TemaService,
    private router : Router,
    private route : ActivatedRoute

  ) { 

  }
  ngOnInit(){
    window.scroll(0,0)

    if(environment.token == ''){      
      this.router.navigate(['/entrar'])

    }

    this.idTema = this.route.snapshot.params['id']
    this.findByIdTema(this.idTema)
  }

  //metodo para buscar tema por ID
  findByIdTema(id : number){
    this.temaService.getByIdTema(id).subscribe((resp : Tema) => {
      this.tema = resp
    })
  }

  //Metodo para apagar Tema por ID
  apagarTema(){
    this.temaService.deleteTema(this.idTema).subscribe(( )=>{
      alert('Tema Apagado!!')
      this.router.navigate(['/tema'])
    })
  }



}
