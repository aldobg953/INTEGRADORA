package com.registro.usuarios.modelo.dto;

import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile file;
    private String desc_breve;
    private String lang;
    private Long id_c_traduccion;

    public CarreraDTO(){

    }

    public CarreraDTO(Long id_carrera, String nombre, String informacion, String roadmap, float costo,
            String horario_especifico, boolean bilingue, int cantidad_periodos, String porque_estudiar,
            String donde_trabajar, String como_desemp, Long universidad, Long area, Long modalidad, Long periodoEscolar,
            Long horario, String desc_breve) {
        this.id_carrera = id_carrera;
        this.nombre = nombre;
        this.informacion = informacion;
        this.roadmap = roadmap;
        this.costo = costo;
        this.horario_especifico = horario_especifico;
        this.bilingue = bilingue;
        this.cantidad_periodos = cantidad_periodos;
        this.porque_estudiar = porque_estudiar;
        this.donde_trabajar = donde_trabajar;
        this.como_desemp = como_desemp;
        this.universidad = universidad;
        this.area = area;
        this.modalidad = modalidad;
        this.periodoEscolar = periodoEscolar;
        this.horario = horario;
        this.desc_breve = desc_breve;
    }

    
}
