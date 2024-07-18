package com.registro.usuarios.modelo.dto;

import lombok.Data;

@Data
public class EspecialidadDTO {
    private Long id_especialidad;
    private String nombre;
    private String descripcion_breve;
    private String informacion;
    private String roadmap;
    private float costo;
    private String horario_especifico;
    private boolean bilingue; 
    private Long id_e_traduccion;
    private String lang;
    private Long carrera;
    private Long modalidad;
    private Long periodoEscolar; 
    private Long horario;

}
