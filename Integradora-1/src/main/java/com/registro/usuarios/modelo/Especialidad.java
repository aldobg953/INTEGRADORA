package com.registro.usuarios.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

//Se almacenan las especialidades de las carreras, ejemplo: desarrollo de software de la carrera tecnologias de la informacion(utch)
@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_especialidad;
    private String nombre;
    private String descripcion_breve;

    @Column(length = 600)
    private String informacion;
    private String roadmap;
    private float costo;
    private String horario_especifico;
    private boolean bilingue; 

    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "fk_modalidad")
    private Modalidad modalidad;

    @ManyToOne
    @JoinColumn(name = "fk_periodo_escolar")
    private PeriodoEscolar periodoEscolar; 

    @ManyToOne
    @JoinColumn(name = "fk_horario")
    private Horario horario;

}
