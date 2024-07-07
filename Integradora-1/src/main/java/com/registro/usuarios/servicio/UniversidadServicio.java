package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.repositorio.UniversidadRepositorio;

@Service
public class UniversidadServicio {
    
    @Autowired
    UniversidadRepositorio universidadRepositorio;

    public Optional<Universidad> getUniversidadesById(Long id){
        return universidadRepositorio.findById(id);
    }

    public List<Universidad> getAllUniversidades(){
        return universidadRepositorio.findAll();
    }

}
