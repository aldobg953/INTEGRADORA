package com.registro.usuarios.modelo.dto;

import lombok.Data;

@Data
public class MotorResultados {
    private Long id;
    private String nombre;
    private String tipo;
    public MotorResultados(Long id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public MotorResultados() {
    }

    
}
