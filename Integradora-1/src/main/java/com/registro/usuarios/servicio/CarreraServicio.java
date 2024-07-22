package com.registro.usuarios.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.dto.CarreraDTO;
import com.registro.usuarios.modelo.traducciones.CarreraTraduccion;
import com.registro.usuarios.repositorio.AreaRepositorio;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.ForoRepositorio;
import com.registro.usuarios.repositorio.HorarioRepositorio;
import com.registro.usuarios.repositorio.ModalidadRepositorio;
import com.registro.usuarios.repositorio.PeriodoEscolarRepositorio;
import com.registro.usuarios.repositorio.UniversidadRepositorio;
import com.registro.usuarios.repositorio.traducciones.CarreraTradRepositorio;

@Service
public class CarreraServicio {
    
    @Autowired
    private CarreraRepositorio carreraRepositorio;
    
    @Autowired
    private ForoRepositorio foroRepositorio;

    @Autowired
    private UniversidadRepositorio universidadRepositorio;

    @Autowired
    private AreaRepositorio areaRepositorio;

    @Autowired
    private ModalidadRepositorio modalidadRepositorio;

    @Autowired
    private PeriodoEscolarRepositorio periodoEscolarRepositorio;

    @Autowired
    private HorarioRepositorio horarioRepositorio;

    @Autowired
    private CarreraTradRepositorio carreraTradRepositorio;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Carrera getCarreraById(Long id, String lang){
        if(lang.equals("es")){
            return carreraRepositorio.findById(id).get();
        }else{
            return aplicarTraduccion(carreraRepositorio.findById(id).get(), lang);
        }
        
    }

    public List<Carrera> getCarrerasByArea(Long id, String lang){
        List<Carrera> carreras = carreraRepositorio.findByArea(id);
        return carreras.stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    }

    public List<Carrera> getCarrerasByUniversidad(Long id, String lang){
        return carreraRepositorio.findByUniversidad(id).stream()
        .map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    }

    public List<Carrera> getCarrerasByUniversidadAndLang(Long id, String lang) {
        List<Carrera> carreras = carreraRepositorio.findByUniversidad(id);
        if(lang.equals("es")){
            return carreras;
        }
        return carreras.stream()
            .map(carrera -> aplicarTraduccion(carrera, lang))
            .collect(Collectors.toList());
    }

    public Carrera aplicarTraduccion(Carrera carrera, String lang) {
        carrera.getArea().cambiarIdioma(lang);
        carrera.getHorario().cambiarIdioma(lang);
        carrera.getModalidad().cambiarIdioma(lang);
        carrera.getPeriodoEscolar().cambiarIdioma(lang);
        if(!lang.equals("es")){
            CarreraTraduccion traduccion = carrera.getTraducciones().stream()
                .filter(t -> t.getLang().equals(lang))
                .findFirst()
                .orElse(null);

            if (traduccion != null) {
                carrera.setNombre(traduccion.getNombre());
                carrera.setInformacion(traduccion.getInformacion());
                carrera.setHorario_especifico(traduccion.getHorario_especifico());
                carrera.setPorque_estudiar(traduccion.getPorque_estudiar());
                carrera.setDonde_trabajar(traduccion.getDonde_trabajar());
                carrera.setComo_desemp(traduccion.getComo_desemp());
                carrera.setDesc_breve(traduccion.getDesc_breve());
            }
        }
        return carrera;
    }


    public List<Foro> getForoByCarrera(Long id){
         return foroRepositorio.findByCarrera(id);
    }

    public Carrera guardarCarrera(CarreraDTO carreraDTO) {
        Carrera newCarrera;
        try {
            Carrera carrera = new Carrera(carreraDTO.getNombre(), carreraDTO.getInformacion(),carreraDTO.getRoadmap(),
            carreraDTO.getCosto(), carreraDTO.getHorario_especifico(), carreraDTO.isBilingue(),carreraDTO.getCantidad_periodos(),
            carreraDTO.getPorque_estudiar(),carreraDTO.getDonde_trabajar(),carreraDTO.getComo_desemp(),
            universidadRepositorio.getById(carreraDTO.getUniversidad()),areaRepositorio.getById(carreraDTO.getArea()),
            modalidadRepositorio.getById(carreraDTO.getModalidad()),periodoEscolarRepositorio.getById(carreraDTO.getPeriodoEscolar()),
            horarioRepositorio.getById(carreraDTO.getHorario()),carreraDTO.getDesc_breve(),0L);
            newCarrera = carreraRepositorio.save(carrera);
        } catch (Exception e) {
            newCarrera = null;
        }
        return newCarrera;
    }

    public List<Carrera> getAllCarreras(String lang){
        List<Carrera> carreras = carreraRepositorio.findAll();
        return carreras.stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    }

    public List<Carrera> getAllCarreras(Collection<Rol> roles,Long id_universidad, String lang){
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
        if(esSuperUsuario){
            return getAllCarreras(lang);
        }else{
            return getCarrerasByUniversidad(id_universidad, lang);
        }
    }

    public CarreraDTO getCarreraDTOById(Long id){
        Carrera carrera = carreraRepositorio.findById(id).get();
        CarreraDTO carreraDTO = new CarreraDTO(carrera.getId_carrera(), carrera.getNombre(),carrera.getInformacion(),carrera.getRoadmap(),
        carrera.getCosto(),carrera.getHorario_especifico(),carrera.isBilingue(),carrera.getCantidad_periodos(),carrera.getPorque_estudiar(),
        carrera.getDonde_trabajar(),carrera.getComo_desemp(),carrera.getUniversidad().getId_universidad(),carrera.getArea().getId_area(),
        carrera.getModalidad().getId_modalidad(),carrera.getPeriodoEscolar().getId_periodo_escolar(),carrera.getHorario().getId_horario(),
        carrera.getDesc_breve());
        return carreraDTO;
    }

    public boolean modificarCarrera(CarreraDTO carreraDTO) {
        try {
            Carrera carrera = carreraRepositorio.getById(carreraDTO.getId_carrera());
            carrera.setNombre(carreraDTO.getNombre());
            carrera.setInformacion(carreraDTO.getInformacion());
            carrera.setCosto(carreraDTO.getCosto());
            carrera.setHorario_especifico(carreraDTO.getHorario_especifico());
            carrera.setBilingue(carreraDTO.isBilingue());
            carrera.setCantidad_periodos(carreraDTO.getCantidad_periodos());
            carrera.setRoadmap(carreraDTO.getRoadmap());
            carrera.setPorque_estudiar(carreraDTO.getPorque_estudiar());
            carrera.setDonde_trabajar(carreraDTO.getDonde_trabajar());
            carrera.setComo_desemp(carreraDTO.getComo_desemp());
            carrera.setUniversidad(universidadRepositorio.getById(carreraDTO.getUniversidad()));
            carrera.setArea(areaRepositorio.getById(carreraDTO.getArea()));
            carrera.setModalidad(modalidadRepositorio.getById(carreraDTO.getModalidad()));
            carrera.setPeriodoEscolar(periodoEscolarRepositorio.getById(carreraDTO.getPeriodoEscolar()));
            carrera.setHorario(horarioRepositorio.getById(carreraDTO.getHorario()));
            carrera.setDesc_breve(carreraDTO.getDesc_breve());
            carreraRepositorio.save(carrera);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean eliminarcarrera(Long id){
        carreraRepositorio.deleteById(id);
        return true;
    }

    //obtiene el id y nombre de todas las carreras
    public List<CarreraDTO> getAllCarreraDTO(Collection<Rol> roles, Long id_universidad, String lang) {
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
    
        return esSuperUsuario ? getAllCarrerasDTO(lang) : getCarrerasDTOByUniversidad(id_universidad, lang);
    }
    
    private List<CarreraDTO> getAllCarrerasDTO(String lang) {
        List<Carrera> carreras = carreraRepositorio.findAll().stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
        return carreras.stream()
                .map(this::convertirACarreraDTO)
                .collect(Collectors.toList());
    }
    
    private List<CarreraDTO> getCarrerasDTOByUniversidad(Long id_universidad, String lang) {
        List<Carrera> carreras = carreraRepositorio.findAll().stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
        return carreras.stream()
                .filter(original -> original.getUniversidad().getId_universidad().equals(id_universidad))
                .map(this::convertirACarreraDTO)
                .collect(Collectors.toList());
    }
    
    private CarreraDTO convertirACarreraDTO(Carrera original) {
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setId_carrera(original.getId_carrera());
        carreraDTO.setNombre(original.getNombre());
        return carreraDTO;
    }
    
    public CarreraDTO getCarreraTraduccion(Long id_carrera, String lang){
        List<CarreraTraduccion> carreraTraduccionList = carreraTradRepositorio.findByCarreraIdAndLang(id_carrera,lang);
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setId_carrera(id_carrera);
        carreraDTO.setLang(lang);
        if(!carreraTraduccionList.isEmpty()){
            CarreraTraduccion carreraTraduccion = carreraTraduccionList.get(0);
            carreraDTO.setId_c_traduccion(carreraTraduccion.getId_c_traduccion());
            carreraDTO.setNombre(carreraTraduccion.getNombre());
            carreraDTO.setHorario_especifico(carreraTraduccion.getHorario_especifico());
            carreraDTO.setInformacion(carreraTraduccion.getInformacion());
            carreraDTO.setPorque_estudiar(carreraTraduccion.getPorque_estudiar());
            carreraDTO.setDonde_trabajar(carreraTraduccion.getDonde_trabajar());
            carreraDTO.setComo_desemp(carreraTraduccion.getComo_desemp());
            carreraDTO.setDesc_breve(carreraTraduccion.getDesc_breve());
        }
        return carreraDTO;
    }

    public boolean guardarTraduccionCarrera(CarreraDTO carreraDTO){
        CarreraTraduccion carreraTraduccion = new CarreraTraduccion(carreraDTO.getId_c_traduccion()
        ,carreraRepositorio.getById(carreraDTO.getId_carrera()), carreraDTO.getLang(), carreraDTO.getNombre(), 
        carreraDTO.getHorario_especifico(), carreraDTO.getInformacion(), carreraDTO.getPorque_estudiar(), 
        carreraDTO.getDonde_trabajar(), carreraDTO.getComo_desemp(), carreraDTO.getDesc_breve());

        try {
            carreraTradRepositorio.save(carreraTraduccion);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void incrementarContador(Long id) {
        Carrera carrera = carreraRepositorio.findById(id).get();
        carrera.setContador(carrera.getContador() + 1);
        carreraRepositorio.save(carrera);
    }

    public List<Carrera> getTop10CarrerasByContador(String lang) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return carreraRepositorio.findTop10ByOrderByContadorDesc(pageRequest).stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    }

    public List<Carrera> buscarCarreras(Long idModalidad, Long idHorario, Long idUniversidad, Long idArea, Integer bilingue, String lang) {
        System.out.println("idModalidad: " + idModalidad);
        System.out.println("idHorario: " + idHorario);
        System.out.println("idUniversidad: " + idUniversidad);
        System.out.println("idArea: " + idArea);
        System.out.println("bilingue: " + bilingue);
    
        Specification<Carrera> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
    
            if (idModalidad != null && idModalidad > 0) {
                predicates.add(criteriaBuilder.equal(root.get("modalidad").get("id_modalidad"), idModalidad));
            }
    
            if (idHorario != null && idHorario > 0) {
                predicates.add(criteriaBuilder.equal(root.get("horario").get("id_horario"), idHorario));
            }
    
            if (idUniversidad != null && idUniversidad > 0) {
                predicates.add(criteriaBuilder.equal(root.get("universidad").get("id_universidad"), idUniversidad));
            }
    
            if (idArea != null && idArea > 0) {
                predicates.add(criteriaBuilder.equal(root.get("area").get("id_area"), idArea));
            }
    
            if (bilingue != null && bilingue >= 0 && bilingue <= 1) {  // Asume que 0 y 1 son valores válidos
                Predicate p = bilingue == 1 ? criteriaBuilder.isTrue(root.get("bilingue")) : criteriaBuilder.isFalse(root.get("bilingue"));
                predicates.add(p);
            }
    
            Predicate finalPredicate = predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    
            return finalPredicate;
        };
    
        List<Carrera> carreras = carreraRepositorio.findAll(spec).stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    
        return carreras;
    }
    
    
    
}
