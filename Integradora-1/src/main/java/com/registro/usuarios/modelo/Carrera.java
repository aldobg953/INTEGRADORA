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

//Almacena las carreras universitarias
@Data
@Entity
@Table(name = "carreras")
public class Carrera {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrera;
    private String nombre;

    @Column(length = 600)
    private String informacion;
    private String roadmap;
    private float costo;
    private String horario_especifico;
    private boolean bilingue;
    private int cantidad_periodos;
    @Column(length = 1000)
    private String porque_estudiar;
    @Column(length = 1000)
    private String donde_trabajar;
    @Column(length = 1000)
    private String como_desemp; //como desempeñar la carrera

    @ManyToOne
    @JoinColumn(name = "fk_universidad")
    private Universidad universidad;

    @ManyToOne
    @JoinColumn(name = "fk_area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "fk_modalidad")
    private Modalidad modalidad;
    
    @ManyToOne
    @JoinColumn(name = "fk_periodo_escolar")
    private PeriodoEscolar periodoEscolar;

    @ManyToOne
    @JoinColumn(name = "fk_horario")
    private Horario horario;

    public Carrera(){

    }

    public Carrera(Long id_carrera, String nombre, String informacion, String roadmap, float costo,
            String horario_especifico, boolean bilingue, int cantidad_periodos, String porque_estudiar,
            String donde_trabajar, String como_desemp, Universidad universidad, Area area, Modalidad modalidad,
            PeriodoEscolar periodoEscolar, Horario horario) {
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
    }

    public Carrera(String nombre, String informacion, String roadmap, float costo, String horario_especifico,
            boolean bilingue, int cantidad_periodos, String porque_estudiar, String donde_trabajar, String como_desemp,
            Universidad universidad, Area area, Modalidad modalidad, PeriodoEscolar periodoEscolar, Horario horario) {
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
    }

    
}
