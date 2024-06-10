package com.registro.usuarios.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;

@Controller
@RequestMapping("/carreras")
public class CarreraControlador {
    
    @Autowired
    private AreaServicio areaServicio;

    @Autowired
    private CarreraServicio carreraServicio;

    @GetMapping("/area/{id}")
    public String mostrarUniversidad( Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        List<Carrera> carreras = carreraServicio.getCarrerasByArea(id);
        String area = carreras.get(0).getArea().getNombre_area();
        model.addAttribute("carreras", carreras);
        model.addAttribute("area", area);
        return "carreras/carreasByArea";
    }

    @GetMapping("/areas")
    public String mostrarAreas( Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Area> areas = areaServicio.getAllAreas();
        model.addAttribute("areas",areas); 
        return "carreras/areas";
    }
}
