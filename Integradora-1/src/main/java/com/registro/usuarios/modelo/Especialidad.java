package com.registro.usuarios.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.registro.usuarios.modelo.traducciones.EspecialidadTraduccion;

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
    private Long id_e_traduccion;
    private String lang;
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

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EspecialidadTraduccion> traducciones;

    public Especialidad() {
    }

    public Especialidad(Long id_especialidad, String nombre, String descripcion_breve, String informacion,
            String roadmap, float costo, String horario_especifico, boolean bilingue,
            Carrera carrera, Modalidad modalidad, PeriodoEscolar periodoEscolar, Horario horario) {
        this.id_especialidad = id_especialidad;
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.informacion = informacion;
        this.roadmap = roadmap;
        this.costo = costo;
        this.horario_especifico = horario_especifico;
        this.bilingue = bilingue;
        this.carrera = carrera;
        this.modalidad = modalidad;
        this.periodoEscolar = periodoEscolar;
        this.horario = horario;
    }

    public Especialidad(String nombre, String descripcion_breve, String informacion, String roadmap, float costo,
            String horario_especifico, boolean bilingue, Carrera carrera,
            Modalidad modalidad, PeriodoEscolar periodoEscolar, Horario horario) {
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.informacion = informacion;
        this.roadmap = roadmap;
        this.costo = costo;
        this.horario_especifico = horario_especifico;
        this.bilingue = bilingue;
        this.carrera = carrera;
        this.modalidad = modalidad;
        this.periodoEscolar = periodoEscolar;
        this.horario = horario;
    }

}
