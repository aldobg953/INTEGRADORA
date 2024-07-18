package com.registro.usuarios.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private CarreraServicio carreraServicio;
	
	@GetMapping("/login")
	public String iniciarSesion(@RequestParam(value = "lang", required = false) String lang, Model model) {
		model.addAttribute("lang", lang);
		return "login";
	}
	
	@GetMapping("/index")
	public String verPaginaDeInicio(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		List<Carrera> carreras = carreraServicio.getAllCarreras(usuario.getLang());
		List<Long> roleIds = usuario.getRoles().stream()
                                       .map(Rol::getId_rol)
                                       .collect(Collectors.toList());
		model.addAttribute("carreras", carreras);
		model.addAttribute("roles",roleIds );
		model.addAttribute("usuario", usuario);
		return "index";
	}
	
	@GetMapping("/")
	public String redirigirIndex(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		return "redirect:/index?lang="+usuario.getLang();
	}

	@GetMapping("/inicio")
	public String inicio() {
		return "inicio";
	}


	@GetMapping("/contactanos")
	public String contactanos(@RequestParam(value = "lang", required = false) String lang, Model model){
		model.addAttribute("lang", lang);
		return "contactanos";
	}
	@GetMapping("/acercade")
	public String acercade(@RequestParam(value = "lang", required = false) String lang, Model model){
		model.addAttribute("lang", lang);
		return "acercade";
	}
}
