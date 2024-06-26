package com.registro.usuarios.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "foro")
public class Foro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foro;
    @Column(length = 600)
    private String comentario;
    private int calificacion;
    private Long fk_carrera;
    private Long fk_especialidad;

    /*@ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "fk_especialidad")
    private Especialidad especialidad;*/

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
}
