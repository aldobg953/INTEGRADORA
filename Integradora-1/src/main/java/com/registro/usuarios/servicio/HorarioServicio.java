package com.registro.usuarios.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.registro.usuarios.modelo.Horario;
import com.registro.usuarios.repositorio.HorarioRepositorio;

@Service
public class HorarioServicio {
    
    @Autowired
    private HorarioRepositorio horarioRepositorio;

    public List<Horario> getAllHorarios(String lang) {
        return horarioRepositorio.findAll().stream()
            .map(horario -> {
                horario.cambiarIdioma(lang);
                return horario;
            })
            .collect(Collectors.toList());
    }
}
