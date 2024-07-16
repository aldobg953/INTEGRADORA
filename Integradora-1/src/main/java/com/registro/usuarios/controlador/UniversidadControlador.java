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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.UniversidadServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/universidades")
public class UniversidadControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UniversidadServicio universidadServicio;
    @Autowired
    private CarreraServicio carreraServicio;
	
	@GetMapping
	public String mostrarIndiceUniversidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Universidad> universidades = universidadServicio.getAllUniversidades(usuario.getLang());
        List<Long> roleIds = usuario.getRoles().stream()
                                       .map(Rol::getId_rol)
                                       .collect(Collectors.toList());
		model.addAttribute("roles",roleIds );
        model.addAttribute("usuario", usuario);
        model.addAttribute("universidades", universidades);
		return "universidades/universidades";
	}

    @GetMapping("/universidad/{id}")
    public String mostrarUniversidad( Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername()); 
        List<Carrera> carreras = carreraServicio.getCarrerasByUniversidadAndLang(id, usuario.getLang()); 
        Optional<Universidad> universidad = universidadServicio.getUniversidadById(id, usuario.getLang());
        List<Long> roleIds = usuario.getRoles().stream()
                                       .map(Rol::getId_rol)
                                       .collect(Collectors.toList());
        model.addAttribute("roles",roleIds );
        model.addAttribute("usuario", usuario);
        model.addAttribute("universidad",universidad.get()); 
        model.addAttribute("carreras",carreras); 
        return "universidades/universidad";
    }
	
}
