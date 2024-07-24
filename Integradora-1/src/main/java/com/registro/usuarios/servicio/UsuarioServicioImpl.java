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

import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.UsuarioRegistroDTO;
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
			int firstSpaceIndex = registroDTO.getNombre().indexOf(' ');
			String nombre = registroDTO.getNombre().substring(0, firstSpaceIndex);
			String apellido = registroDTO.getNombre().substring(firstSpaceIndex + 1);
			usuario = new Usuario(nombre, 
			apellido, registroDTO.getEmail(),
			passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(RolRespository.getById(1L)),true,false,registroDTO.getLang(), 0L);
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
		Usuario usuario =usuarioRepositorio.findByEmail(username);
		usuario.setCarrerasFavoritas(null);
        return usuario;
    }
	@Override
	public Usuario findByEmailwithFavoritos(String username){
		return usuarioRepositorio.findByEmail(username);
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario){
		usuarioRepositorio.save(usuario);
		return true;
	}

	@Override
	public boolean actualizarPass (String email, String oldPass, String newPass){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		
		if(passwordEncoder.matches(oldPass, usuario.getPassword())){
			usuario.setPassword(passwordEncoder.encode(newPass));
			usuarioRepositorio.save(usuario);
			return true;
		}else{
			return false;
		}

	}

	@Override
	public boolean resetPassword (Usuario usuario){
		try {
			usuario.setPassword(passwordEncoder.encode("12345"));
			usuarioRepositorio.save(usuario);
		} catch (Exception e) {
			return false;
		}
		return true;
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
				return "exito";
			}else{
				return "La contraseña ingresada es incorrecta";
			}
		}else{
			return "El correo electronico esta en uso.";
		}
	}

	@Override
	public void actualizarDarkmode(boolean darkmode, String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		usuario.setDarkmode(darkmode);
		usuarioRepositorio.save(usuario);
	}
	@Override
	public void actualizarIdioma(String idioma, String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		usuario.setLang(idioma);
		usuarioRepositorio.save(usuario);
	}
	
	@Override
	public Usuario getById(Long id){
		return usuarioRepositorio.getById(id);
	}

	@Override
	public UsuarioRegistroDTO getUsuarioRegistroDTO(Long id){
		Usuario usuario = usuarioRepositorio.getById(id);
		boolean rolAdmin = false;
		boolean rolSuper = false;
		for (Rol rol : usuario.getRoles()) {
			if(rol.getId_rol() == 2){
				rolAdmin = true;
			}
			if(rol.getId_rol() == 3){
				rolSuper = true;
			}
		}

		return new UsuarioRegistroDTO(usuario.getId_usuario(), usuario.getNombre(), 
		usuario.getApellidoP(),usuario.getEmail(), usuario.getPassword(),rolSuper,rolAdmin,usuario.getId_universidad(),usuario.getLang());
	}

	@Override
	public boolean actualizarUsuarioDTO( UsuarioRegistroDTO usuarioRegistroDTO){
		Usuario usuario = usuarioRepositorio.getById(usuarioRegistroDTO.getId_usuario());
		usuario.setNombre(usuarioRegistroDTO.getNombre());
		usuario.setApellidoP(usuarioRegistroDTO.getApellidoP());
		boolean esSuperUsuario = usuario.getRoles().stream().anyMatch(rol -> rol.getId_rol() == 3);
		boolean esUsuarioAdmin = usuario.getRoles().stream().anyMatch(rol -> rol.getId_rol() == 2);
		if(usuarioRegistroDTO.isSuperUsuario()){
			if(!esSuperUsuario){
				usuario.addRol(new Rol(3L, "ROLE_SUPER"));				
			}
		}else{
			if(esSuperUsuario){
				usuario.removeRol(3L);
			}
		}

		if(usuarioRegistroDTO.isUsuarioAdmin()){
			if(!esUsuarioAdmin){
				usuario.addRol(new Rol(2L, "ROLE_ADMIN"));	
			}
		}else{
			if (esUsuarioAdmin) {
				usuario.removeRol(2L);
			}
		}
		if(usuarioRegistroDTO.getIdUniversidad()!=0){
			usuario.setId_universidad(usuarioRegistroDTO.getIdUniversidad());
		}else{
			usuario.setId_universidad(null);
		}
		try {
			usuarioRepositorio.save(usuario);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean cambiarFotoUsuario(Usuario usuario, String fotoPerfil){
		
		usuario.setFoto_perfil(fotoPerfil);
		try {
			usuarioRepositorio.save(usuario);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
