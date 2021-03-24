package com.blogpessoal.blog.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogpessoal.blog.model.Usuario;
import com.blogpessoal.blog.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException
	{
		Optional<Usuario> user =  repository.findByUsuario(userName);
		user.orElseThrow(()-> new UsernameNotFoundException(userName + "not found."));
		return user.map(UserDetailsImpl::new).get();
	}
}
