package com.registro.usuarios.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Horario;
import com.registro.usuarios.modelo.Modalidad;
import com.registro.usuarios.modelo.PeriodoEscolar;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.CarreraDTO;
import com.registro.usuarios.modelo.dto.UniversidadDTO;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.EspecialidadServicio;
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

    @Autowired
    EspecialidadServicio especialidadServicio;

    @Value("${file.upload-dir}")
    private String uploadDir;

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
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadResumen();
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
        try {
            Carrera res = carreraServicio.guardarCarrera(carreraDTO);
            if(res!=null){  
                Path path = Paths.get(uploadDir).resolve(res.getId_carrera() + ".jpg").normalize();
                Files.copy(carreraDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }else{
                return "redirect:/administrador/crearcarrera?error";
            }
        } catch (IOException ex) {
            return "redirect:/administrador/crearcarrera?error";
        }
        return "redirect:/administrador/crearcarrera?exito";
    }

    @GetMapping("/modificarCarrera")
    private String modificarCarrera(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Carrera> carreras = carreraServicio.getAllCarreras();
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreras", carreras);
        return "administrador/modificarcarrera";
    }

    @GetMapping("/modificarcarrera/{id}")
    private String modificarCarreraEsp(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        CarreraDTO carrera = carreraServicio.getCarreraDTOById(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
       
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadResumen();
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar();
        List<Area> areas = areaServicio.getAllAreas();
        List<Horario> horarios = horarioServicio.getAllHoriarios();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades();

        model.addAttribute("usuario", usuario);
        model.addAttribute("carrera", carrera);
        model.addAttribute("areas", areas);
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("universidades", universidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        return "administrador/modificarcarreraesp";
    }

    @PostMapping("/postmodificarcarrera")
    private String postModificarCarrera(@ModelAttribute CarreraDTO carreraDTO){
        if(!carreraDTO.getFile().isEmpty()){
            try {
                Path path = Paths.get(uploadDir).resolve(carreraDTO.getId_carrera() + ".jpg").normalize();
                Files.copy(carreraDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?error";
            }
        }
        if(carreraServicio.modificarCarrera(carreraDTO)){
            return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?exito";
        }
        return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?error";
    }


    //FALTA AGREGAR VALIDACIONES
    @GetMapping("/eliminarcarrera/{id}")
    private String eliminarcarrera(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        carreraServicio.eliminarcarrera(id);
        File archivo = new File(uploadDir+"/"+id+".jpg");
        archivo.delete();
        return "redirect:/administrador/modificarCarrera";
    }

    @GetMapping("/crearespecialidad")
    private String crearespecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<CarreraDTO> carreras = carreraServicio.getAllCarreraDTO();
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar();
        List<Horario> horarios = horarioServicio.getAllHoriarios();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades();
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        model.addAttribute("especialidad", new Especialidad());
        return "administrador/crearespecialidad";
    }

    @PostMapping("/postguardarespecialidad")
    private String postGuardarEspecialidad(@ModelAttribute Especialidad especialidad){
        Especialidad especialidadNueva = especialidadServicio.guardarEspecialidad(especialidad);
        return "redirect:/administrador/modificarespecialidad/"+especialidadNueva.getId_especialidad()+"?exito";
    }

    @GetMapping("/modificarespecialidad")
    private String modificarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Especialidad> especialidades = especialidadServicio.getAllEspecilidades();
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("usuario", usuario);
        return "administrador/modificarespecialidades";
    }


    @GetMapping("/modificarespecialidad/{id}")
    private String modificarEspecialidadID(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<CarreraDTO> carreras = carreraServicio.getAllCarreraDTO();
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar();
        List<Horario> horarios = horarioServicio.getAllHoriarios();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades();
        Especialidad especialidad = especialidadServicio.getEspecialidadById(id).get();
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        model.addAttribute("especialidad", especialidad);
        return "administrador/modificarespecialidad";
    }

     //FALTA AGREGAR VALIDACIONES
     @GetMapping("/eliminarespecialidad/{id}")
     private String eliminarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        
        if(especialidadServicio.deleteEspecialidad(id)){
            return "redirect:/administrador/modificarespecialidad?exito";
        }
        return "redirect:/administrador/modificarespecialidad?error";
     }

     @GetMapping("/crearuniversidad")
     private String crearUniversidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("universidad", new UniversidadDTO());
        model.addAttribute("usuario", usuario);
        return "administrador/crearuniversidad";
     }


}
