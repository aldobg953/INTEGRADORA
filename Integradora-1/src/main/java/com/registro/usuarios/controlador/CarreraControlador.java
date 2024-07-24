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

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.Horario;
import com.registro.usuarios.modelo.Modalidad;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.AreaDTO;
import com.registro.usuarios.modelo.dto.CalificacionesDto;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
import com.registro.usuarios.servicio.AreaServicio;
import com.registro.usuarios.servicio.CarreraServicio;
import com.registro.usuarios.servicio.EspecialidadServicio;
import com.registro.usuarios.servicio.FavoritoService;
import com.registro.usuarios.servicio.ForoServicio;
import com.registro.usuarios.servicio.HorarioServicio;
import com.registro.usuarios.servicio.ModalidadServicio;
import com.registro.usuarios.servicio.UniversidadServicio;
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

    @Autowired 
    private ModalidadServicio modalidadServicio;

    @Autowired 
    private HorarioServicio horarioServicio;

    @Autowired 
    private UniversidadServicio universidadServicio;

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/carrera/{id}")
    public String mostrarCarrera(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        carreraServicio.incrementarContador(id);
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Carrera carrera = carreraServicio.getCarreraById(id, usuario.getLang());
        List<Foro> foros = carreraServicio.getForoByCarrera(id);
        CalificacionesDto califGnral = foroServicio.obtenerCalifCarrera(id);
        List<Especialidad> especialidades = especialidadServicio.getEspecialidadByCarrera(id,usuario.getLang());
        
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("usuario", usuario);
        model.addAttribute("carrera", carrera);
        model.addAttribute("foros", foros);
        model.addAttribute("califGnral", califGnral);
        return "carreras/carrera";
    }

    @GetMapping("/area/{id}")
    public String mostrarCarreraByArea( Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        
        
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<Carrera> carreras = carreraServicio.getCarrerasByArea(id,usuario.getLang());
        String area = carreras.get(0).getArea().getNombre_area();
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreras", carreras);
        model.addAttribute("area", area);
        return "carreras/carreasByArea";
    }

    @GetMapping("/areas")
    public String mostrarAreas( Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        List<AreaDTO> areas = areaServicio.getAllAreas(usuario.getLang());
        model.addAttribute("usuario", usuario);
        model.addAttribute("areas",areas); 
        return "carreras/areas";
    }

    @PostMapping("/agregarComentario")
    public String agregarComentario(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                         @RequestParam("id_carrera") Long id_carrera,
                                         @RequestParam("comentarioNuevo") String comentarioNuevo,
                                         @RequestParam("calificacion") int calificacion
                                            ) {
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        foroServicio.crearForoCarrera(id_carrera, userDetails.getUsername(), comentarioNuevo, calificacion);
        return "redirect:/carreras/carrera/" + id_carrera+"?lang="+usuario.getLang();
    }

    @GetMapping("/especialidad/{id}")
    public String mostrarEspecialidad(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        Especialidad especialidad = especialidadServicio.getEspecialidadById(id, usuario.getLang());
        model.addAttribute("especialidad", especialidad);
        model.addAttribute("usuario", usuario);
        return "carreras/especialidad";
    }

    @GetMapping("/all")
    public String mostrarCarreras(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        String lang = usuario.getLang();
        List<Carrera> carreras = carreraServicio.getAllCarreras(lang);
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(lang);
        List<Horario> horarios = horarioServicio.getAllHorarios(lang);
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadesResumen();
        List<AreaDTO> areas = areaServicio.getAllAreas(lang);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("horarios", horarios);
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreras", carreras);
        model.addAttribute("universidades", universidades);
        model.addAttribute("areas", areas);
        return "carreras/allcarreras";
    }

    @GetMapping("/buscarCarreras")
    public String buscarCarreras(
        @RequestParam(name = "search", required = false) String search,
        @RequestParam(name = "idModalidad", required = false) Long idModalidad,
        @RequestParam(name = "idHorario", required = false) Long idHorario,
        @RequestParam(name = "idUniversidad", required = false) Long idUniversidad,
        @RequestParam(name = "idArea", required = false) Long idArea,
        @RequestParam(name = "bilingue", required = false) Integer bilingue,
        @RequestParam(name = "lang", required = false) String lang, // Agregado para recibir el parámetro lang
        @AuthenticationPrincipal UserDetails userDetails,
        Model model) {

        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        lang = usuario.getLang();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(lang);
        List<Horario> horarios = horarioServicio.getAllHorarios(lang);
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadesResumen();
        List<AreaDTO> areas = areaServicio.getAllAreas(lang);
        
        List<Carrera> carreras = carreraServicio.buscarCarreras(idModalidad, idHorario, idUniversidad, idArea, bilingue, lang);

        model.addAttribute("modalidades", modalidades);
        model.addAttribute("horarios", horarios);
        model.addAttribute("universidades", universidades);
        model.addAttribute("areas", areas);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        
        return "carreras/allcarreras";
    }

    @GetMapping("/all/{id}")
    public String mostrarCarrerasComparacion(Model model, @AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        String lang = usuario.getLang();
        List<Carrera> carreras = carreraServicio.getAllCarreras(lang);
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(lang);
        List<Horario> horarios = horarioServicio.getAllHorarios(lang);
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadesResumen();
        List<AreaDTO> areas = areaServicio.getAllAreas(lang);
        Carrera carreraAComparar = carreraServicio.getCarreraById(id, lang);
        model.addAttribute("carreraAComparar", carreraAComparar);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("horarios", horarios);
        model.addAttribute("usuario", usuario);
        model.addAttribute("carreras", carreras);
        model.addAttribute("universidades", universidades);
        model.addAttribute("areas", areas);
        return "carreras/seleccionarCarreraComp";
    }

    @GetMapping("/buscarCarrerasComp")
    public String buscarCarrerasComp(
        @RequestParam(name = "search", required = false) String search,
        @RequestParam(name = "idModalidad", required = false) Long idModalidad,
        @RequestParam(name = "idHorario", required = false) Long idHorario,
        @RequestParam(name = "idUniversidad", required = false) Long idUniversidad,
        @RequestParam(name = "idArea", required = false) Long idArea,
        @RequestParam(name = "bilingue", required = false) Integer bilingue,
        @RequestParam(name = "lang", required = false) String lang, // Agregado para recibir el parámetro lang
        @RequestParam(name = "id_carrera_comp", required = false) Long id_carrera_comp,
        @AuthenticationPrincipal UserDetails userDetails,
        Model model) {

        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        lang = usuario.getLang();
        List<Modalidad> modalidades = modalidadServicio.getAllModalidades(lang);
        List<Horario> horarios = horarioServicio.getAllHorarios(lang);
        List<UniversidadResumen> universidades = universidadServicio.getAllUniversidadesResumen();
        List<AreaDTO> areas = areaServicio.getAllAreas(lang);
        
        List<Carrera> carreras = carreraServicio.buscarCarreras(idModalidad, idHorario, idUniversidad, idArea, bilingue, lang);
        Carrera carreraAComparar = carreraServicio.getCarreraById(id_carrera_comp, lang);
        
        model.addAttribute("carreraAComparar", carreraAComparar);
        model.addAttribute("modalidades", modalidades);
        model.addAttribute("horarios", horarios);
        model.addAttribute("universidades", universidades);
        model.addAttribute("areas", areas);
        model.addAttribute("carreras", carreras);
        model.addAttribute("usuario", usuario);
        
        return "carreras/seleccionarCarreraComp";
    }

    @GetMapping("/compararCarreras")
    public String compararCarreras(
        @RequestParam(name = "id_carrera_1", required = true) Long id_carrera_1,
        @RequestParam(name = "id_carrera_2", required = true) Long id_carrera_2,
        @RequestParam(name = "lang", required = false) String lang,
        @AuthenticationPrincipal UserDetails userDetails,
        Model model) {
            Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
            Carrera carrera1 = carreraServicio.getCarreraById(id_carrera_1, lang);
            Carrera carrera2 = carreraServicio.getCarreraById(id_carrera_2, lang);
            model.addAttribute("carrera1", carrera1);
            model.addAttribute("carrera2", carrera2);
            model.addAttribute("usuario", usuario);
            return "carreras/compararCarreras";
    }

    @PostMapping("/carreras/agregarFav")
    public String agregarFavorito(@RequestParam("carreraId") Long carreraId, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmailwithFavoritos(userDetails.getUsername());
        favoritoService.agregarFavorito(usuario, carreraId);
        return "redirect:/carreras/carrera/"+carreraId+"?lang="+usuario.getLang();
    }

    @PostMapping("/carreras/quitarFav")
    public String quitarFavorito(@RequestParam("carreraId") Long carreraId, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioServicio.findByEmailwithFavoritos(userDetails.getUsername());
        favoritoService.eliminarFavorito(usuario, carreraId);
        return "redirect:/carreras/carrera/"+carreraId+"?lang="+usuario.getLang();
    }
}
