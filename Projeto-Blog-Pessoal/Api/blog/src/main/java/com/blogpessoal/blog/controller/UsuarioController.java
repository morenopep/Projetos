package com.blogpessoal.blog.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.blog.model.UserLogin;
import com.blogpessoal.blog.model.Usuario;
import com.blogpessoal.blog.service.UsuarioService;


@RestController 
@CrossOrigin(origins = "*", allowedHeaders = "*") 
@RequestMapping("/usuarios")
@Transactional
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@PostMapping ("/logar")
	public ResponseEntity<UserLogin> autentication (@Valid @RequestBody Optional<UserLogin> user)
	{
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> creation (@Valid @RequestBody Usuario usuario)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}

}