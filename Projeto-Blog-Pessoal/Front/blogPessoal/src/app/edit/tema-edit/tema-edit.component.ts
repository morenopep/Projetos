import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from 'src/app/model/Tema';
import { TemaService } from 'src/app/service/tema.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-tema-edit',
  templateUrl: './tema-edit.component.html',
  styleUrls: ['./tema-edit.component.css']
})
export class TemaEditComponent implements OnInit {
  tema: Tema = new Tema()

  //injetando as dependecias no construtor
  constructor(
    private temaService : TemaService, 
    private router : Router,
    private route : ActivatedRoute ) { 
    }

  ngOnInit(){
    window.scroll(0,0)

    if(environment.token == ''){
      this.router.navigate(['/entrar'])
    }

    let id = this.route.snapshot.params['id']
    this.findByIdTema(id)
  }
  
  //metodo para buscar tema pelo ID
  // Esse tema vem da rota que esta ativa no momento e usamos a dependecia route injetada no construtor e criamos uma variavel dentro de ngonit para receber o parametro que vem na rota ID
  
  findByIdTema(id : number){    
    this.temaService.getByIdTema(id).subscribe(( resp : Tema) =>{
      this.tema = resp
    })
  }

  //Metodo para atualizar o Tema
  atualizarTema(){
    this.temaService.putTema(this.tema).subscribe(( resp: Tema) =>{
      this.tema = resp
      alert("Tema Atualizado!")
      this.router.navigate(['/tema'])
    })
  }

  
}
