package com.registro.usuarios.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Modalidad;
import com.registro.usuarios.repositorio.ModalidadRepositorio;

@Service
public class ModalidadServicio {
    
    @Autowired
    private ModalidadRepositorio modalidadRepositorio;

    public List<Modalidad> getAllModalidades(String lang) {
        return modalidadRepositorio.findAll().stream()
            .map(modalidad -> {
                modalidad.cambiarIdioma(lang);
                return modalidad;
            })
            .collect(Collectors.toList());
    }
}
