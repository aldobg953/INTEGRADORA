package com.registro.usuarios.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Horario;
import com.registro.usuarios.modelo.Modalidad;
import com.registro.usuarios.modelo.PeriodoEscolar;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.CarreraDTO;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.HorarioServicio;
import com.registro.usuarios.servicio.ModalidadServicio;
import com.registro.usuarios.servicio.PeriodoEscolarServicio;
import com.registro.usuarios.servicio.UniversidadServicio;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    UniversidadServicio universidadServicio;

    @Autowired
    CarreraServicio carreraServicio;

    @Autowired
    PeriodoEscolarServicio periodoEscolarServicio;

    @Autowired
    AreaServicio areaServicio;

    @Autowired
    HorarioServicio horarioServicio;

    @Autowired
    ModalidadServicio modalidadServicio;

    @GetMapping
    private String administrador(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
         List<Long> roleIds = usuario.getRoles().stream()
                                       .map(Rol::getId_rol)
                                       .collect(Collectors.toList());
        model.addAttribute("roles",roleIds );                               
        return "administrador/administrador";
    }

    @GetMapping("/crearcarrera")
    private String crearcarrera(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Universidad> universidades = universidadServicio.getAllUniversidades();
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar();
        List<Area> areas = areaServicio.getAllAreas();
        List<Horario> horarios = horarioServicio.getAllHoriarios();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades();
        model.addAttribute("carrera", new CarreraDTO());
        model.addAttribute("areas", areas);
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("universidades", universidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        model.addAttribute("usuario", usuario);
        return "administrador/crearCarrera";
    }

    @PostMapping("/postguardarcarrera")
    private String postGuardarCarrera(@ModelAttribute CarreraDTO carreraDTO){
        String res = carreraServicio.guardarCarrera(carreraDTO);
        return "redirect:/administrador/crearcarrera?"+res;
    }
}
