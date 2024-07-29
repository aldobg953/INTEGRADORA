package com.registro.usuarios.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Horario;
import com.registro.usuarios.modelo.Modalidad;
import com.registro.usuarios.modelo.PeriodoEscolar;
import com.registro.usuarios.modelo.Pregunta;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.AreaDTO;
import com.registro.usuarios.modelo.dto.CarreraDTO;
import com.registro.usuarios.modelo.dto.EspecialidadDTO;
import com.registro.usuarios.modelo.dto.UniversidadDTO;
import com.registro.usuarios.modelo.dto.UsuarioRegistroDTO;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.EspecialidadServicio;
import com.registro.usuarios.servicio.HorarioServicio;
import com.registro.usuarios.servicio.ModalidadServicio;
import com.registro.usuarios.servicio.PeriodoEscolarServicio;
import com.registro.usuarios.servicio.PreguntasServicio;
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

    @Autowired
    PreguntasServicio preguntasServicio;

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
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadResumen(usuario.getRoles(), usuario.getId_universidad());
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar(usuario.getLang());
        List<AreaDTO> areas = areaServicio.getAllAreas(usuario.getLang());
        List<Horario> horarios = horarioServicio.getAllHorarios(usuario.getLang());
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(usuario.getLang());
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
    private String postGuardarCarrera(@ModelAttribute CarreraDTO carreraDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        try {
            Carrera res = carreraServicio.guardarCarrera(carreraDTO);
            if(res!=null){  
                Path path = Paths.get(uploadDir+"/carreras").resolve(res.getId_carrera() + ".jpg").normalize();
                Files.copy(carreraDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }else{
                return "redirect:/administrador/crearcarrera?error&lang="+usuario.getLang();
            }
        } catch (IOException ex) {
            return "redirect:/administrador/crearcarrera?error&lang="+usuario.getLang();
        }
        return "redirect:/administrador/crearcarrera?exito&lang="+usuario.getLang();
    }

    @GetMapping("/modificarCarrera")
    private String modificarCarrera(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Carrera> carreras = carreraServicio.getAllCarreras(usuario.getRoles(),usuario.getId_universidad(), usuario.getLang());
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreras", carreras);
        return "administrador/modificarcarrera";
    }

    @GetMapping("/modificarcarrera/{id}")
    private String modificarCarreraEsp(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        CarreraDTO carrera = carreraServicio.getCarreraDTOById(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
       
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadResumen(usuario.getRoles(), usuario.getId_universidad());
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar(usuario.getLang());
        List<AreaDTO> areas = areaServicio.getAllAreas(usuario.getLang());
        List<Horario> horarios = horarioServicio.getAllHorarios(usuario.getLang());
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(usuario.getLang());

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
    private String postModificarCarrera(@ModelAttribute CarreraDTO carreraDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(!carreraDTO.getFile().isEmpty()){
            try {
                Path path = Paths.get(uploadDir + "/carreras").resolve(carreraDTO.getId_carrera() + ".jpg").normalize();
                Files.copy(carreraDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?error&lang="+usuario.getLang();
            }
        }
        if(carreraServicio.modificarCarrera(carreraDTO)){
            return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/modificarcarrera/" + carreraDTO.getId_carrera() + "?error&lang="+usuario.getLang();
    }


    //FALTA AGREGAR VALIDACIONES
    @GetMapping("/eliminarcarrera/{id}")
    private String eliminarcarrera(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        carreraServicio.eliminarcarrera(id);
        File archivo = new File(uploadDir+"/carreras/"+id+".jpg");
        archivo.delete();
        return "redirect:/administrador/modificarCarrera?lang="+usuario.getLang();
    }

    @GetMapping("/crearespecialidad")
    private String crearespecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<CarreraDTO> carreras = carreraServicio.getAllCarreraDTO(usuario.getRoles(),usuario.getId_universidad(), usuario.getLang());
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar(usuario.getLang());
        List<Horario> horarios = horarioServicio.getAllHorarios(usuario.getLang());
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(usuario.getLang());
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        model.addAttribute("especialidad", new Especialidad());
        return "administrador/crearespecialidad";
    }

    @PostMapping("/postguardarespecialidad")
    private String postGuardarEspecialidad(@ModelAttribute Especialidad especialidad, @AuthenticationPrincipal UserDetails userDetails){
        
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(especialidad.getCarrera()==null){
            return "redirect:/administrador/crearespecialidad?error&lang="+usuario.getLang();
            
        }else{
            Especialidad especialidadNueva = especialidadServicio.guardarEspecialidad(especialidad);
            return "redirect:/administrador/modificarespecialidad/"+especialidadNueva.getId_especialidad()+"?exito&lang="+usuario.getLang();
        }
    }

    @GetMapping("/modificarespecialidad")
    private String modificarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Especialidad> especialidades = especialidadServicio.getEspecialidadesbyUsuario(usuario.getRoles(),usuario.getId_universidad(), usuario.getLang());
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("usuario", usuario);
        return "administrador/modificarespecialidades";
    }


    @GetMapping("/modificarespecialidad/{id}")
    private String modificarEspecialidadID(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<CarreraDTO> carreras = carreraServicio.getAllCarreraDTO(usuario.getRoles(),usuario.getId_universidad(), usuario.getLang());
        List<PeriodoEscolar> allPeriodoEscolar = periodoEscolarServicio.getAllPeriodoEscolar(usuario.getLang());
        List<Horario> horarios = horarioServicio.getAllHorarios(usuario.getLang());
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(usuario.getLang());
        EspecialidadDTO especialidad = especialidadServicio.getEspecialidadDTO(id, "es");
        model.addAttribute("horarios", horarios);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("allPeriodoEscolar", allPeriodoEscolar);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        model.addAttribute("especialidad", especialidad);
        return "administrador/modificarespecialidad";
    }
    @PostMapping("/postmodificarespecialidad")
    private String postModificarEspecialidad(@ModelAttribute EspecialidadDTO especialidadDTO,@AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(especialidadServicio.guardarEspecialidad(especialidadDTO) != null){
            return "redirect:/administrador/modificarespecialidad/"+especialidadDTO.getId_especialidad()+"?exito&lang="+usuario.getLang();
        }else{
            return "redirect:/administrador/modificarespecialidad/"+especialidadDTO.getId_especialidad()+"?error&lang="+usuario.getLang();
        }
    }

     @GetMapping("/eliminarespecialidad/{id}")
     private String eliminarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(especialidadServicio.deleteEspecialidad(id)){
            return "redirect:/administrador/modificarespecialidad?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/modificarespecialidad?error&lang="+usuario.getLang();
     }

     @GetMapping("/crearuniversidad")
     private String crearUniversidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("universidad", new UniversidadDTO());
        model.addAttribute("usuario", usuario);
        return "administrador/crearuniversidad";
     }

    @PostMapping("/postguardaruniversidad")
    private String postGuardarUniversidad(@ModelAttribute UniversidadDTO universidadDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(universidadServicio.existeUniversidad(universidadDTO.getNombre_abreviado())){
            return "redirect:/administrador/crearuniversidad?error&lang="+usuario.getLang();
        }
        try {
            Path dir = Paths.get(uploadDir,universidadDTO.getNombre_abreviado());
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Path path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("logo.jpg").normalize();
            if(!universidadDTO.getLogo().isEmpty()){
                Files.copy(universidadDTO.getLogo().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            if(!universidadDTO.getPortada().isEmpty()){
                path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("portada.jpg").normalize();
                Files.copy(universidadDTO.getPortada().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            if(!universidadDTO.getImagen1().isEmpty()){
                path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("imagen1.jpg").normalize();
                Files.copy(universidadDTO.getImagen1().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            Universidad universidadnueva = universidadServicio.guardarUniversidad(universidadDTO); 
            if(universidadnueva != null){
                return "redirect:/administrador/modificaruniversidad/"+universidadnueva.getId_universidad()+"?exito&lang="+usuario.getLang();
            }else{
                return "redirect:/administrador/crearuniversidad?error&lang="+usuario.getLang();
            }
        } catch (Exception e) {
            return "redirect:/administrador/crearuniversidad?error&lang="+usuario.getLang();
        } 
    }


    @PostMapping("/postmodificaruniversidad")
    private String postModificarUniversidad(@ModelAttribute UniversidadDTO universidadDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        try {
            Path dir = Paths.get(uploadDir,universidadDTO.getNombre_abreviado());
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Path path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("logo.jpg").normalize();
            if(!universidadDTO.getLogo().isEmpty()){
                Files.copy(universidadDTO.getLogo().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            if(!universidadDTO.getPortada().isEmpty()){
                path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("portada.jpg").normalize();
                Files.copy(universidadDTO.getPortada().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            if(!universidadDTO.getImagen1().isEmpty()){
                path = Paths.get(uploadDir+"/"+universidadDTO.getNombre_abreviado()).resolve("imagen1.jpg").normalize();
                Files.copy(universidadDTO.getImagen1().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            if(universidadServicio.guardarUniversidad(universidadDTO) != null){
                return "redirect:/administrador/modificaruniversidad/"+universidadDTO.getId_universidad()+"?exito&lang="+usuario.getLang();
            }else{
                return "redirect:/administrador/crearuniversidad?error&lang="+usuario.getLang();
            }
        } catch (Exception e) {
            return "redirect:/administrador/crearuniversidad?error&lang="+usuario.getLang();
        } 
    }


    @GetMapping("/modificaruniversidad")
    private String modificarUniversidad(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Universidad> universidades = universidadServicio.getAllUniversidades(usuario.getLang());
        boolean esSuperUsuario = usuario.getRoles().stream().anyMatch(rol -> rol.getId_rol() == 3);
        if(esSuperUsuario){
            model.addAttribute("universidades", universidades);
            model.addAttribute("usuario", usuario);
            return "administrador/modificaruniversidades";
        }else{
            return "redirect:/administrador/modificaruniversidad/"+usuario.getId_universidad()+"?lang="+usuario.getLang();
        }
        
    }

    @GetMapping("/modificaruniversidad/{id}")
    private String modificarUniversidadID(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        UniversidadDTO universidad = universidadServicio.getUniversidadDTO(id);
        model.addAttribute("universidad", universidad);
        model.addAttribute("usuario", usuario);
        return "administrador/modificaruniversidad";
    }

    @GetMapping("/eliminaruniversidad/{id}")
     private String eliminarUniversidad(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(universidadServicio.eliminarUniversidad(id)){
            return "redirect:/administrador/modificaruniversidad?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/modificaruniversidad?error&lang="+usuario.getLang();
     }
    @GetMapping("/usuarios")
    private String administrarUsuario(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", usuario);
        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/{id}")
    private String administrarUsuarioID(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        UsuarioRegistroDTO usuarioEdit = usuarioServicio.getUsuarioRegistroDTO(id);
        List<UniversidadResumen> universidadDTO = universidadServicio.getAllUniversidadResumen(usuario.getRoles(),usuario.getId_universidad());
        model.addAttribute("universidadDTO", universidadDTO);
        model.addAttribute("usuarioEdit", usuarioEdit);
        model.addAttribute("usuario", usuario);
        return "administrador/usuarioedit";
    }

    @PostMapping("/postmodificarusuario")
    private String postModificarUsuario(@ModelAttribute UsuarioRegistroDTO usuarioRegistroDTO,  @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(usuarioServicio.actualizarUsuarioDTO(usuarioRegistroDTO)){
            return "redirect:/administrador/usuarios/"+usuarioRegistroDTO.getId_usuario() + "?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/usuarios/"+usuarioRegistroDTO.getId_usuario()+"?error&lang="+usuario.getLang();
    }

    @GetMapping("/resetearPassword/{id}")
    private String resetearPassword(@AuthenticationPrincipal UserDetails userDetails,  @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.getById(id);
        if(usuarioServicio.resetPassword(usuario)){
            return "redirect:/administrador/usuarios/" + usuario.getId_usuario()+"?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/usuarios/" + usuario.getId_usuario()+"?error&lang="+usuario.getLang();
    }

    @GetMapping("/traduccion")
    private String traducciones(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        boolean esSuperUsuario = usuario.getRoles().stream().anyMatch(rol -> rol.getId_rol() == 3);
        List<Universidad> universidades = null;

        if(esSuperUsuario){
            universidades = universidadServicio.getAllUniversidades(usuario.getLang());
            
        }else{
            universidades = new ArrayList<>();
            universidades.add(universidadServicio.getUniversidadById(usuario.getId_universidad(), usuario.getLang()));
        }
        List<Especialidad> especialidades = especialidadServicio.getEspecialidadesbyUsuario(usuario.getRoles(),usuario.getId_universidad(),usuario.getLang());
        List<Carrera> carreras = carreraServicio.getAllCarreras(usuario.getRoles(),usuario.getId_universidad(),usuario.getLang());
        model.addAttribute("carreras", carreras);
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("universidades", universidades);
        model.addAttribute("usuario", usuario);
        return "administrador/traduccion";
    }

    @GetMapping("/traduccionUni/{id}/{lang}")
    public String crearModificarTraduccionUni(@AuthenticationPrincipal UserDetails userDetails,  @PathVariable("id") Long id,
                                            @PathVariable("lang") String lang, Model model){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        UniversidadDTO universidadTraduccion = universidadServicio.getUniTraduccion(id, lang);
        model.addAttribute("usuario", usuario);
        model.addAttribute("universidadTraduccion", universidadTraduccion);
        return "administrador/traduccionUni";
    }   

    @PostMapping("/postGuardarTraduccionUni")
    private String postGuardarTraduccionUni(@ModelAttribute UniversidadDTO universidadDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(universidadServicio.guardarTraduccionUni(universidadDTO)){
            return "redirect:/administrador/traduccionUni/"+universidadDTO.getId_universidad()+"/"+universidadDTO.getLang()+"?exito&lang="+usuario.getLang();
        }else{
            return "redirect:/administrador/traduccionUni/"+universidadDTO.getId_universidad()+"/"+universidadDTO.getLang()+"?error&lang="+usuario.getLang();
        }
    }

    @GetMapping("/traduccionCarr/{id}/{lang}")
    public String crearModificarTraduccionCarr(@AuthenticationPrincipal UserDetails userDetails,  @PathVariable("id") Long id,
                                            @PathVariable("lang") String lang, Model model){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        CarreraDTO carreraTraduccion = carreraServicio.getCarreraTraduccion(id, lang);
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreraTraduccion", carreraTraduccion);
        return "administrador/traduccionCarrera";
    }   

    @PostMapping("/postGuardarTraduccionCarr")
    private String postGuardarTraduccionUni(@ModelAttribute CarreraDTO carreraDTO, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(carreraServicio.guardarTraduccionCarrera(carreraDTO)){
            return "redirect:/administrador/traduccionCarr/"+carreraDTO.getId_carrera()+"/"+carreraDTO.getLang()+"?exito&lang="+usuario.getLang();
        }else{
            return "redirect:/administrador/traduccionCarr/"+carreraDTO.getId_carrera()+"/"+carreraDTO.getLang()+"?error&lang="+usuario.getLang();
        }
    }

    @GetMapping("/traduccionEsp/{id}/{lang}")
    public String crearModificarTraduccionEsp(@AuthenticationPrincipal UserDetails userDetails,  @PathVariable("id") Long id,
                                            @PathVariable("lang") String lang, Model model){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Especialidad especialidadTraduccion = especialidadServicio.getEspecialidadTraduccion(id, lang);
        model.addAttribute("usuario", usuario);
        model.addAttribute("especialidadTraduccion", especialidadTraduccion);
        return "administrador/traduccionespecialidad";
    }   

    @PostMapping("/postGuardarTraduccionEsp")
    private String postGuardarTraduccionEsp(@ModelAttribute Especialidad especialidad, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(especialidadServicio.guardarTraduccionEsp(especialidad)){
            return "redirect:/administrador/traduccionEsp/"+especialidad.getId_especialidad()+"/"+especialidad.getLang()+"?exito&lang="+usuario.getLang();
        }else{
            return "redirect:/administrador/traduccionEsp/"+especialidad.getId_especialidad()+"/"+especialidad.getLang()+"?error&lang="+usuario.getLang();
        }
    }

    @GetMapping("/eliminarusuario/{id}")
    private String eliminarUsuario(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(usuarioServicio.eliminarUsuario(id)){
            return "redirect:/administrador/usuarios?exito&lang="+usuario.getLang();
        }
        return "redirect:/administrador/usuarios?error&lang="+usuario.getLang();
    }


    @GetMapping("/test")
    private String agregarPreguntas(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Pregunta> preguntas = preguntasServicio.getAllPreguntas(usuario.getLang());
        model.addAttribute("usuario", usuario);
        model.addAttribute("preguntas", preguntas);
        return "administrador/testadmin";
    }

    @GetMapping("/modificarpregunta/{id}")
    private String modificarPreguntaID(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Pregunta pregunta = preguntasServicio.getPreguntaByID(id);
        System.out.println(pregunta.getRespuestas().get(0).getText());
        List<AreaDTO> areasDTO = areaServicio.getAllAreas(usuario.getLang());
        model.addAttribute("areasDTO", areasDTO);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("usuario", usuario);
        return "administrador/modificarpregunta";
    }

    @PostMapping("/postGuardarPregunta")
    public String actualizarPregunta(@ModelAttribute("pregunta") Pregunta pregunta, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(preguntasServicio.guardarPregunta(pregunta)){
            return "redirect:/administrador/modificarpregunta/"+pregunta.getId_pregunta()+"?exito&lang="+usuario.getLang();
        }else{
            return "redirect:/administrador/modificarpregunta/"+pregunta.getId_pregunta()+"?error&lang="+usuario.getLang();
        }
    }
}
