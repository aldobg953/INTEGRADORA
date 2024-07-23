package com.registro.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/test")
public class TestControlador {

	@Autowired
	UsuarioServicio usuarioServicio;

	@GetMapping
	public String mostrarIntroTest(Model model, @AuthenticationPrincipal UserDetails userDetails){
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		model.addAttribute("usuario", usuario);
		return "test/presentacionTest";
	}

	@GetMapping("/inicio")
	public String mostrarTest(Model model, @AuthenticationPrincipal UserDetails userDetails){
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		model.addAttribute("usuario", usuario);
		return "test/mostrarTest";
	}
}
