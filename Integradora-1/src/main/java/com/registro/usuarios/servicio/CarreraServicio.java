package com.registro.usuarios.servicio;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return aplicarTraduccion(carreraRepositorio.getById(id), lang);
        }
        
    }

    public List<Carrera> getCarrerasByArea(Long id, String lang){
        List<Carrera> carreras = carreraRepositorio.findByArea(id);
        return carreras.stream().map(carrera -> aplicarTraduccion(carrera, lang))
        .collect(Collectors.toList());
    }

    public List<Carrera> getCarrerasByUniversidad(Long id){
        return carreraRepositorio.findByUniversidad(id);
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
        CarreraTraduccion traduccion = carrera.getTraducciones().stream()
            .filter(t -> t.getLang().equals(lang))
            .findFirst()
            .orElse(null);

        if (traduccion != null) {
            carrera.getArea().cambiarIdioma(lang);
            carrera.getHorario().cambiarIdioma(lang);
            carrera.getModalidad().cambiarIdioma(lang);
            carrera.getPeriodoEscolar().cambiarIdioma(lang);
            carrera.setNombre(traduccion.getNombre());
            carrera.setInformacion(traduccion.getInformacion());
            carrera.setHorario_especifico(traduccion.getHorario_especifico());
            carrera.setPorque_estudiar(traduccion.getPorque_estudiar());
            carrera.setDonde_trabajar(traduccion.getDonde_trabajar());
            carrera.setComo_desemp(traduccion.getComo_desemp());
            carrera.setDesc_breve(traduccion.getDesc_breve());
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
            horarioRepositorio.getById(carreraDTO.getHorario()),carreraDTO.getDesc_breve());
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
            return getCarrerasByUniversidad(id_universidad);
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
            Carrera carrera = new Carrera(carreraDTO.getId_carrera(),carreraDTO.getNombre(), carreraDTO.getInformacion(),carreraDTO.getRoadmap(),
            carreraDTO.getCosto(), carreraDTO.getHorario_especifico(), carreraDTO.isBilingue(),carreraDTO.getCantidad_periodos(),
            carreraDTO.getPorque_estudiar(),carreraDTO.getDonde_trabajar(),carreraDTO.getComo_desemp(),
            universidadRepositorio.getById(carreraDTO.getUniversidad()),areaRepositorio.getById(carreraDTO.getArea()),
            modalidadRepositorio.getById(carreraDTO.getModalidad()),periodoEscolarRepositorio.getById(carreraDTO.getPeriodoEscolar()),
            horarioRepositorio.getById(carreraDTO.getHorario()),carreraDTO.getDesc_breve());
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
        List<CarreraTraduccion> carreraTraduccionList = carreraTradRepositorio.findByUniversidadIdAndLang(id_carrera,lang);
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

    @Transactional
    public void incrementarContador(Long id) {
        Carrera carrera = carreraRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        carrera.setContador(carrera.getContador() + 1);
        carreraRepositorio.save(carrera);
    }

    public List<Carrera> getTop10CarrerasByContador(String lang) {
    PageRequest pageRequest = PageRequest.of(0, 10);
    return carreraRepositorio.findTop10ByOrderByContadorDesc(pageRequest).stream().map(carrera -> aplicarTraduccion(carrera, lang))
    .collect(Collectors.toList());
}
}
