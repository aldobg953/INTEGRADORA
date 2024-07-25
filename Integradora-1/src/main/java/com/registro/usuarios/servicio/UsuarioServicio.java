package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.UsuarioRegistroDTO;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public boolean cambiarFotoUsuario(Usuario usuario, String fotoPerfil);

	public List<Usuario> listarUsuarios();

	Usuario findByEmail(String username);
	
	public boolean actualizarUsuario(Usuario usuario);

	public boolean actualizarPass (String email, String oldPass, String newPass);

	public boolean resetPassword (Usuario usuario);

	public String actualizarEmail(String oldEmail, String newEmail , String password);

	public void actualizarDarkmode(boolean darkmode, String email);

	public void actualizarIdioma(String idioma, String email);

	public Usuario getById(Long id);

	public UsuarioRegistroDTO getUsuarioRegistroDTO(Long id);

	public boolean actualizarUsuarioDTO( UsuarioRegistroDTO usuarioRegistroDTO);
}
