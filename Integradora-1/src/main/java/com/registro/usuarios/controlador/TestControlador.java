package com.registro.usuarios.controlador;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.Pregunta;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.PreguntasServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/test")
public class TestControlador {

	@Autowired
	UsuarioServicio usuarioServicio;

	@Autowired
	PreguntasServicio preguntasServicio;

	@Autowired
	AreaServicio areaServicio;

	@GetMapping
	public String mostrarIntroTest(Model model, @AuthenticationPrincipal UserDetails userDetails){
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		model.addAttribute("usuario", usuario);
		return "test/presentacionTest";
	}

	@GetMapping("/inicio")
	public String mostrarTest(Model model, @AuthenticationPrincipal UserDetails userDetails){
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
		List<Pregunta> preguntas = preguntasServicio.getAllPreguntas(usuario.getLang());
		model.addAttribute("usuario", usuario);
		model.addAttribute("preguntas", preguntas);
		return "test/mostrarTest";
	}

	@PostMapping("/mostrarresultado")
    public String processTestResults(@RequestParam Map<String, String> allParams, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Map<Long, Integer> areaCount = new HashMap<>();
        
        for (String key : allParams.keySet()) {
            if (key.startsWith("pregunta-")) {
                Long areaId = Long.parseLong(allParams.get(key));
                areaCount.put(areaId, areaCount.getOrDefault(areaId, 0) + 1);
            }
        }
        
        Long mostSelectedArea = areaCount.entrySet()
                                         .stream()
                                         .max(Comparator.<Map.Entry<Long, Integer>>comparingInt(Map.Entry::getValue)
                                             .thenComparing((entry1, entry2) -> Long.compare(entry2.getKey(), entry1.getKey())))
                                         .map(Map.Entry::getKey)
                                         .orElse(null);
										 
        Area area = areaServicio.getAreaByidLang(mostSelectedArea, usuario.getLang());
		model.addAttribute("area", area);
        model.addAttribute("usuario", usuario);
        return "test/resultadotest";
    }
}
