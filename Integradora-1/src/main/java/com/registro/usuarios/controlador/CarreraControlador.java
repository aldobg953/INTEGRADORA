package com.registro.usuarios.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.CalificacionesDto;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.EspecialidadServicio;
import com.registro.usuarios.servicio.ForoServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/carreras")
public class CarreraControlador {
    
    @Autowired
    private AreaServicio areaServicio;

    @Autowired
    private CarreraServicio carreraServicio;

    @Autowired
    private ForoServicio foroServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EspecialidadServicio especialidadServicio;

    @GetMapping("/carrera/{id}")
    public String mostrarCarrera(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        Carrera carrera = carreraServicio.getCarreraById(id).get();
        List<Foro> foros = carreraServicio.getForoByCarrera(id);
        CalificacionesDto califGnral = foroServicio.obtenerCalifCarrera(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Especialidad> especialidades = especialidadServicio.getEspecialidadByCarrera(id);

        model.addAttribute("especialidades", especialidades);
        model.addAttribute("usuario", usuario);
        model.addAttribute("carrera", carrera);
        model.addAttribute("foros", foros);
        model.addAttribute("califGnral", califGnral);
        return "carreras/carrera";
    }

    @GetMapping("/area/{id}")
    public String mostrarCarreraByArea( Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
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

    @PostMapping("/agregarComentario")
    public String agregarComentario(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                         @RequestParam("id_carrera") Long id_carrera,
                                         @RequestParam("comentarioNuevo") String comentarioNuevo,
                                         @RequestParam("calificacion") int calificacion
                                            ) {
        foroServicio.crearForoCarrera(id_carrera, userDetails.getUsername(), comentarioNuevo, calificacion);
        return "redirect:/carreras/carrera/" + id_carrera;
    }

    @GetMapping("/especialidad/{id}")
    public String mostrarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Especialidad especialidad = especialidadServicio.getEspecialidadById(id).get();
        model.addAttribute("especialidad", especialidad);
        model.addAttribute("usuario", usuario);
        return "carreras/especialidad";
    }
}
