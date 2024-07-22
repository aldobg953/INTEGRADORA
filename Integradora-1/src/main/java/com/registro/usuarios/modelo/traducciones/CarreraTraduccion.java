package com.registro.usuarios.modelo.traducciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.registro.usuarios.modelo.Carrera;

import lombok.Data;

@Data
@Entity
@Table(name="carrera_traduccion")
public class CarreraTraduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_c_traduccion;
    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;
    private String lang;
    private String nombre;
    private String horario_especifico;
    @Column(length = 600)
    private String informacion;
    @Column(length = 1000)
    private String porque_estudiar;
    @Column(length = 1000)
    private String donde_trabajar;
    @Column(length = 1000)
    private String como_desemp; 
    @Column(length = 500)
    private String desc_breve;


    public CarreraTraduccion(Long id_c_traduccion, Carrera carrera, String lang,
            String nombre, String horario_especifico, String informacion, String porque_estudiar, String donde_trabajar,
            String como_desemp, String desc_breve) {
        this.id_c_traduccion = id_c_traduccion;
        this.carrera = carrera;
        this.lang = lang;
        this.nombre = nombre;
        this.horario_especifico = horario_especifico;
        this.informacion = informacion;
        this.porque_estudiar = porque_estudiar;
        this.donde_trabajar = donde_trabajar;
        this.como_desemp = como_desemp;
        this.desc_breve = desc_breve;
    }
    public CarreraTraduccion(Carrera carrera, String lang, String nombre,
            String horario_especifico, String informacion, String porque_estudiar, String donde_trabajar,
            String como_desemp, String desc_breve) {
        this.carrera = carrera;
        this.lang = lang;
        this.nombre = nombre;
        this.horario_especifico = horario_especifico;
        this.informacion = informacion;
        this.porque_estudiar = porque_estudiar;
        this.donde_trabajar = donde_trabajar;
        this.como_desemp = como_desemp;
        this.desc_breve = desc_breve;
    }
    public CarreraTraduccion() {
    }

    
}
