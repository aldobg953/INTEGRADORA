package com.registro.usuarios.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registro.usuarios.modelo.dto.MotorResultados;
import com.registro.usuarios.servicio.MotorServicio;

@RestController
@RequestMapping("/api/motor")
public class MotorControlador {
    private final MotorServicio motorServicio;

    public MotorControlador(MotorServicio motorServicio) {
        this.motorServicio = motorServicio;
    }

    @GetMapping
    public ResponseEntity<List<MotorResultados>> search(@RequestParam String query,  @RequestParam(required = false) String lang) {
        List<MotorResultados> results = motorServicio.searchAll(query, lang);
        return ResponseEntity.ok(results);
    }
}
