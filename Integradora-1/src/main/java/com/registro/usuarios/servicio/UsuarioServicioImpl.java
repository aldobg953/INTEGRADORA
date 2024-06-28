package com.registro.usuarios.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.RolRespository;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private RolRespository RolRespository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		//comprobacion
		Usuario usuario = usuarioRepositorio.findByEmail(registroDTO.getEmail());
		if(usuario==null){
			usuario = new Usuario(registroDTO.getNombre(), 
			registroDTO.getApellidoP(),registroDTO.getApellidoM(),registroDTO.getEmail(),
			passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(RolRespository.getById(1L)));
			return usuarioRepositorio.save(usuario);
		}else{
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
    public Usuario findByEmail(String username) {
        return usuarioRepositorio.findByEmail(username);
    }

	@Override
	public boolean actualizarUsuario(Usuario usuario){
		usuarioRepositorio.save(usuario);
		return true;
	}

	@Override
	public boolean actualizarPass (String email, String password){
		String userPassword = usuarioRepositorio.findByEmail(email).getPassword();
		
		System.out.println(passwordEncoder.matches(password, userPassword));
		return false;
	}

	@Override
	public String actualizarEmail(String oldEmail, String newEmail , String password){
		Usuario usuario = usuarioRepositorio.findByEmail(newEmail);
		if (usuario == null) {
			Usuario userPassword = usuarioRepositorio.findByEmail(oldEmail);
			if (passwordEncoder.matches(password, userPassword.getPassword())) {
				userPassword.setEmail(newEmail);
				System.out.println("se actualizo");
				usuarioRepositorio.save(userPassword);
			}else{
				System.out.println("NO SE ACTUALIZO");
			}
		}else{
			System.out.println("NO SE ACTUALIZO PORQUE EXISTE USUARIO");
		}
		return null;
	}

}
