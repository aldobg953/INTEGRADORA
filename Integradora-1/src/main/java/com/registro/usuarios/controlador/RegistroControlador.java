package com.registro.usuarios.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.servicio.UniversidadServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;
	@Autowired
	private UniversidadServicio universidadServicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		Optional<Universidad> universidad =  universidadServicio.getUniversidadesById(3L);
		String direccion = universidad.get().getDireccionGoogle();
		modelo.addAttribute("direccion", direccion);
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
