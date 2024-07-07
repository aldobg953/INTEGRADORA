package com.registro.usuarios.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.UsuarioRegistroDTO;
import com.registro.usuarios.servicio.UsuarioServicio;
import com.registro.usuarios.test.Question;
import com.registro.usuarios.test.Respuestas;


@Controller
@RequestMapping("/test")
public class TestControlador {

    private UsuarioServicio usuarioServicio;
	private List<Question> questions;
	public TestControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
		this.questions = new ArrayList<>();
		List<Respuestas> respuestas = new ArrayList<>();
		List<Respuestas> respuestas2 = new ArrayList<>();
		respuestas.add(new Respuestas(1,"Leer libros",1,1));
		respuestas.add(new Respuestas(2,"Hacer deporte",2,1));
		respuestas.add(new Respuestas(3,"Trabajar con tecnología",3,1));
		respuestas.add(new Respuestas(4,"Ayudar a otras personas",4,1));
		//new String[]{"Leer libros", "Hacer deporte", "Trabajar con tecnología", "Ayudar a otras personas"})
		//new String[]{"Laboratorio", "Aire libre", "Oficina",  "Hospital"})
		respuestas2.add(new Respuestas(5,"Laboratorio",1,2));
		respuestas2.add(new Respuestas(6,"Aire libre",2,2));
		respuestas2.add(new Respuestas(7,"Oficina",3,2));
		respuestas2.add(new Respuestas(8,"Hospital",4,2));
		questions.add(new Question(1L, "¿Cuál de estas actividades prefieres?", respuestas ));
        questions.add(new Question(2L, "¿Qué tipo de ambiente de trabajo prefieres?",respuestas2));
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

	@GetMapping("/testPrincipal")
	public String verPaginaDeInicio(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
            Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
            model.addAttribute("usuario", usuario);
        }
		
		model.addAttribute("questions", questions);
		return "test/testPrincipal";
	}

	@PostMapping("/testResultado")
	public String testResultados(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestParam Map<String, String> allParams){
		if (userDetails != null) {
            Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
            model.addAttribute("usuario", usuario);
        }
		System.out.println(allParams);
		List<Integer> selectedValues = new ArrayList<>();
		for (String value : allParams.values()) {
			selectedValues.add(Integer.parseInt(value));
		}

		model.addAttribute("selectedValues", selectedValues);
		return "test/testResultado";
	}

}
