package com.registro.usuarios.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.registro.usuarios.modelo.traducciones.CarreraTraduccion;

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
    @Column(length = 500)
    private String desc_breve;
    private Long contador;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarreraTraduccion> traducciones;

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

    @ManyToMany(mappedBy = "carrerasFavoritas")
    private Set<Usuario> usuariosFavoritos = new HashSet<>();

    public Carrera(){

    }

    public Carrera(Long id_carrera, String nombre, String informacion, String roadmap, float costo,
            String horario_especifico, boolean bilingue, int cantidad_periodos, String porque_estudiar,
            String donde_trabajar, String como_desemp, Universidad universidad, Area area, Modalidad modalidad,
            PeriodoEscolar periodoEscolar, Horario horario, String desc_breve, Long contador) {
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
        this.contador = contador;
    }

    public Carrera(String nombre, String informacion, String roadmap, float costo, String horario_especifico,
            boolean bilingue, int cantidad_periodos, String porque_estudiar, String donde_trabajar, String como_desemp,
            Universidad universidad, Area area, Modalidad modalidad, PeriodoEscolar periodoEscolar, Horario horario, String desc_breve,
            Long contador) {
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
        this.contador = contador;
    }

    
}
