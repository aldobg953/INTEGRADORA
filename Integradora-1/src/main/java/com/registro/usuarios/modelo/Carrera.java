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


}
