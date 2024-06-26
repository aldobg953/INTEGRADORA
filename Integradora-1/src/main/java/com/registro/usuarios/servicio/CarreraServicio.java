package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.ForoRepositorio;

@Service
public class CarreraServicio {
    
    @Autowired
    private CarreraRepositorio carreraRepositorio;
    
    @Autowired
    private  ForoRepositorio foroRepositorio;

    public List<Carrera> getAllCarreras(){
        return carreraRepositorio.findAll();
    }

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
}
