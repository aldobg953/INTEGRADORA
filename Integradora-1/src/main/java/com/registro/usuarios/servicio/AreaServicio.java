package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.repositorio.AreaRepositorio;

@Service
public class AreaServicio {
    
    @Autowired
    private AreaRepositorio areaRepositorio;

    public List<Area> getAllAreas(){
        return areaRepositorio.findAll();
    }
}
