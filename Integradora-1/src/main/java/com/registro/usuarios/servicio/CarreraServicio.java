package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public String guardarCarrera(CarreraDTO carreraDTO) {
        try {
            Carrera carrera = new Carrera(carreraDTO.getNombre(), carreraDTO.getInformacion(),carreraDTO.getRoadmap(),
            carreraDTO.getCosto(), carreraDTO.getHorario_especifico(), carreraDTO.isBilingue(),carreraDTO.getCantidad_periodos(),
            carreraDTO.getPorque_estudiar(),carreraDTO.getDonde_trabajar(),carreraDTO.getComo_desemp(),
            universidadRepositorio.getById(carreraDTO.getUniversidad()),areaRepositorio.getById(carreraDTO.getArea()),
            modalidadRepositorio.getById(carreraDTO.getModalidad()),periodoEscolarRepositorio.getById(carreraDTO.getPeriodoEscolar()),
            horarioRepositorio.getById(carreraDTO.getHorario()));
            carreraRepositorio.save(carrera);
        } catch (Exception e) {
            return "error";
        }
        return "exito";
    }

}
