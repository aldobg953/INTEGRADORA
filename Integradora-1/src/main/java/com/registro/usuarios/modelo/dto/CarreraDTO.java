package com.registro.usuarios.modelo.dto;

import lombok.Data;

@Data
public class CarreraDTO {
    private Long id_carrera;
    private String nombre;
    private String informacion;
    private String roadmap;
    private float costo;
    private String horario_especifico;
    private boolean bilingue;
    private int cantidad_periodos;
    private String porque_estudiar;
    private String donde_trabajar;
    private String como_desemp; //como desempeñar la carrera
    private Long universidad;
    private Long area;
    private Long modalidad;
    private Long periodoEscolar;
    private Long horario;
}
