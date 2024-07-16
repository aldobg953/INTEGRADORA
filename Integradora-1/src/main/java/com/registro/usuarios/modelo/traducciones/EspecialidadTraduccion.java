package com.registro.usuarios.modelo.traducciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.registro.usuarios.modelo.Especialidad;

import lombok.Data;

@Data
@Entity
@Table(name="especialidad_traduccion")
public class EspecialidadTraduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_e_traduccion;
    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
    private String lang;
    private String nombre;
    private String descripcion_breve;
    @Column(length = 600)
    private String informacion;
}
