package com.registro.usuarios.controlador;

import java.util.List;
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
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.CarreraDetalleDTO;
import com.registro.usuarios.modelo.dto.UniversidadDetalleDTO;
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
        List<UniversidadDetalleDTO> universidadDetalles = universidades.stream()
        .map(universidad -> new UniversidadDetalleDTO(universidad))
        .collect(Collectors.toList());
        model.addAttribute("usuario", usuario);
        model.addAttribute("universidadDetalles", universidadDetalles);
		return "universidades/universidades";
	}

    @GetMapping("/universidad/{id}")
    public String mostrarUniversidad( Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername()); 
        List<Carrera> carreras = carreraServicio.getCarrerasByUniversidadAndLang(id, usuario.getLang()); 
        List<CarreraDetalleDTO> carreraDetalleDTO = carreras.stream().map(carrera -> new CarreraDetalleDTO(carrera)).collect(Collectors.toList());
        Universidad universidad = universidadServicio.getUniversidadById(id, usuario.getLang());
        UniversidadDetalleDTO universidadDetalle = new UniversidadDetalleDTO(universidad);
        model.addAttribute("usuario", usuario);
        model.addAttribute("universidadDetalle",universidadDetalle); 
        model.addAttribute("carreraDetalleDTO",carreraDetalleDTO); 
        return "universidades/universidad";
    }
	
}
