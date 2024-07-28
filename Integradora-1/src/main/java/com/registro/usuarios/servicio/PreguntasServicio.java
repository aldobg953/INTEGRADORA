package com.registro.usuarios.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.modelo.Pregunta;
import com.registro.usuarios.modelo.Respuesta;
import com.registro.usuarios.repositorio.PreguntaRepositorio;
import com.registro.usuarios.repositorio.RespuestaRepositorio;

@Service
public class PreguntasServicio {
    
    @Autowired
    private PreguntaRepositorio preguntaRepositorio;

    @Autowired
    private RespuestaRepositorio respuestaRepositorio;

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

    @Transactional
    public boolean guardarPregunta(Pregunta pregunta) {
        try {
            // Buscar la pregunta existente
            Pregunta preguntaExistente = preguntaRepositorio.findById(pregunta.getId_pregunta()).get();

            // Actualizar los campos de la pregunta
            preguntaExistente.setText(pregunta.getText());
            preguntaExistente.setText_ingles(pregunta.getText_ingles());
            preguntaExistente.setText_frances(pregunta.getText_frances());

            // Manejar las respuestas
            List<Respuesta> respuestasActualizadas = new ArrayList<>();
            for (Respuesta respuesta : pregunta.getRespuestas()) {
                if (respuesta.getId_respuesta() != null) {
                    // Actualizar respuesta existente
                    Respuesta respuestaExistente = respuestaRepositorio.findById(respuesta.getId_respuesta())
                        .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));
                    respuestaExistente.setText(respuesta.getText());
                    respuestaExistente.setText_ingles(respuesta.getText_ingles());
                    respuestaExistente.setText_frances(respuesta.getText_frances());
                    respuestaExistente.setId_area(respuesta.getId_area());
                    respuestasActualizadas.add(respuestaExistente);
                } else {
                    // Nueva respuesta
                    respuesta.setPregunta(preguntaExistente);
                    respuestasActualizadas.add(respuesta);
                }
            }

            // Eliminar respuestas que ya no existen
            preguntaExistente.getRespuestas().removeIf(respuesta -> 
                !respuestasActualizadas.contains(respuesta));

            // Actualizar la lista de respuestas
            preguntaExistente.setRespuestas(respuestasActualizadas);

            // Guardar la pregunta (esto también guardará las respuestas debido a CascadeType.ALL)
            preguntaRepositorio.save(preguntaExistente);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
