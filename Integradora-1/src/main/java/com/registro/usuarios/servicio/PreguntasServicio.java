package com.registro.usuarios.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Pregunta;
import com.registro.usuarios.repositorio.PreguntaRepositorio;

@Service
public class PreguntasServicio {
    
    @Autowired
    private PreguntaRepositorio preguntaRepositorio;

    public List<Pregunta> getAllPreguntas(String lang){
        return preguntaRepositorio.findAll().stream().map(pregunta -> {
            pregunta.cambiarIdioma(lang);
            return pregunta;
        })
        .collect(Collectors.toList());
    }

    public Pregunta getPreguntaByID(Long id){
        return preguntaRepositorio.getById(id);
    }

}
