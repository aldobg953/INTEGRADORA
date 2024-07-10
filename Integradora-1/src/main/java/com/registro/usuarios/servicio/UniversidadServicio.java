package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
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

    public List<UniversidadResumen> getAllUniversidadResumen(){
         return universidadRepositorio.findAll().stream()
                .map(universidad -> {
                    UniversidadResumen resumen = new UniversidadResumen();
                    resumen.setId_universidad(universidad.getId_universidad());
                    resumen.setNombre_completo(universidad.getNombre_completo());
                    return resumen;
                })
                .collect(Collectors.toList());
    }
}
