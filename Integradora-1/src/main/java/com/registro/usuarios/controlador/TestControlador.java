package com.registro.usuarios.controlador;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.controlador.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/test")
public class TestControlador {

    private UsuarioServicio usuarioServicio;

	public TestControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

    @GetMapping
	public String mostrarIndiceTest(Model model, @AuthenticationPrincipal UserDetails userDetails){
         if (userDetails != null) {
            Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
            model.addAttribute("usuario", usuario);
        }
		return "test/indiceTest";
	}
}
