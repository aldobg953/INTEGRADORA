package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "calificacion_carrera")
public class CalificacionCarrera {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_calificacion_carrera;

    private float calificacion;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "fk_especialidad")
    private Especialidad especialidad;


}
