package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.registro.usuarios.modelo.dto.UsuarioRegistroDTO;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	private UsuarioServicio usuarioServicio;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro(@RequestParam(value = "lang", required = false) String lang, Model model) {
		UsuarioRegistroDTO usuarioDTO = new UsuarioRegistroDTO();
		usuarioDTO.setLang(lang);
		model.addAttribute("usuario", usuarioDTO);
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, RedirectAttributes redirectAttributes,
											 Model model) {						
		if(registroDTO.getLang().equals("")){
			registroDTO.setLang("en");
		}
		
		if(usuarioServicio.guardar(registroDTO)==null){
			return "redirect:/registro?error&lang="+registroDTO.getLang();
		}else{
			String mensaje = "Registro exitoso. ¡Ahora puedes iniciar sesión!";
			if(registroDTO.getLang().equals("en")){
				mensaje = "Registration successful, now you can log in!";
			}
			if(registroDTO.getLang().equals("fr")){
				mensaje = "Inscription réussie, vous pouvez maintenant vous connecter !";
			}

			redirectAttributes.addFlashAttribute("mensajeExito", mensaje);
			return "redirect:/login?lang="+registroDTO.getLang();
		}
	}
}
