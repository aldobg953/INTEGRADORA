package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();

	Usuario findByEmail(String username);
	
	public boolean actualizarUsuario(Usuario usuario);

	public boolean actualizarPass (String email, String password);

	public String actualizarEmail(String oldEmail, String newEmail , String password);
}
