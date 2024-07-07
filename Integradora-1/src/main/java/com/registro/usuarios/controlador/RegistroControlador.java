package com.registro.usuarios.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UniversidadServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private UniversidadServicio universidadServicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		Optional<Universidad> universidad =  universidadServicio.getUniversidadesById(3L);
		String direccion = universidad.get().getDireccionGoogle();

		 List<Long> roleIds = usuario.getRoles().stream()
                                       .map(Rol::getId_rol)
                                       .collect(Collectors.toList());
									   
		model.addAttribute("roles",roleIds );
		model.addAttribute("usuario", usuario);
		model.addAttribute("direccion", direccion);
		return "index";
	}

	@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}


	@GetMapping("/contactanos")
	public String contactanos(){
		return "contactanos";
	}
	@GetMapping("/acercade")
	public String acercade(){
		return "acercade";
	}
}
