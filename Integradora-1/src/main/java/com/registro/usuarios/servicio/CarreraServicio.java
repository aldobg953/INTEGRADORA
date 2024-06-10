package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.repositorio.CarreraRepositorio;

@Service
public class CarreraServicio {
    
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    public List<Carrera> getAllCarreras(){
        return carreraRepositorio.findAll();
    }

    public Optional<Carrera> getCarreraById(Long id){
        return carreraRepositorio.findById(id);
    }

    public List<Carrera> getCarrerasByArea(Long id){
        return carreraRepositorio.findByArea(id);
    }
}
