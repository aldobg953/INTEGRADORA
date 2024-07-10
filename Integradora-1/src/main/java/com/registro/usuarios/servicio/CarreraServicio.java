package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.dto.CarreraDTO;
import com.registro.usuarios.repositorio.AreaRepositorio;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.ForoRepositorio;
import com.registro.usuarios.repositorio.HorarioRepositorio;
import com.registro.usuarios.repositorio.ModalidadRepositorio;
import com.registro.usuarios.repositorio.PeriodoEscolarRepositorio;
import com.registro.usuarios.repositorio.UniversidadRepositorio;

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

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Optional<Carrera> getCarreraById(Long id){
        return carreraRepositorio.findById(id);
    }

    public List<Carrera> getCarrerasByArea(Long id){
        return carreraRepositorio.findByArea(id);
    }

    public List<Carrera> getCarrerasByUniversidad(Long id){
        return carreraRepositorio.findByUniversidad(id);
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
            horarioRepositorio.getById(carreraDTO.getHorario()));
            newCarrera = carreraRepositorio.save(carrera);
        } catch (Exception e) {
            newCarrera = null;
        }
        return newCarrera;
    }

    public List<Carrera> getAllCarreras(){
        return carreraRepositorio.findAll();
    }

    public CarreraDTO getCarreraDTOById(Long id){
        Carrera carrera = carreraRepositorio.findById(id).get();
        CarreraDTO carreraDTO = new CarreraDTO(carrera.getId_carrera(), carrera.getNombre(),carrera.getInformacion(),carrera.getRoadmap(),
        carrera.getCosto(),carrera.getHorario_especifico(),carrera.isBilingue(),carrera.getCantidad_periodos(),carrera.getPorque_estudiar(),
        carrera.getDonde_trabajar(),carrera.getComo_desemp(),carrera.getUniversidad().getId_universidad(),carrera.getArea().getId_area(),
        carrera.getModalidad().getId_modalidad(),carrera.getPeriodoEscolar().getId_periodo_escolar(),carrera.getHorario().getId_horario());
        return carreraDTO;
    }

    public boolean modificarCarrera(CarreraDTO carreraDTO) {
        try {
            Carrera carrera = new Carrera(carreraDTO.getId_carrera(),carreraDTO.getNombre(), carreraDTO.getInformacion(),carreraDTO.getRoadmap(),
            carreraDTO.getCosto(), carreraDTO.getHorario_especifico(), carreraDTO.isBilingue(),carreraDTO.getCantidad_periodos(),
            carreraDTO.getPorque_estudiar(),carreraDTO.getDonde_trabajar(),carreraDTO.getComo_desemp(),
            universidadRepositorio.getById(carreraDTO.getUniversidad()),areaRepositorio.getById(carreraDTO.getArea()),
            modalidadRepositorio.getById(carreraDTO.getModalidad()),periodoEscolarRepositorio.getById(carreraDTO.getPeriodoEscolar()),
            horarioRepositorio.getById(carreraDTO.getHorario()));
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
    public List<CarreraDTO> getAllCarreraDTO(){

        return carreraRepositorio.findAll().stream().map(original -> {CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setId_carrera(original.getId_carrera());
        carreraDTO.setNombre(original.getNombre());
        return carreraDTO;
        })
        .collect(Collectors.toList());
    }
}
