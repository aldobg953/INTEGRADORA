package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

// Tipos de periodo escolar: Cuatrimestre, Semestre, Trimestre, Bimestre, Anual, Quimestre, Modulos
@Data
@Entity
public class PeriodoEscolar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_periodo_escolar;
    private String nombre_periodo;
    private String nombre_periodo_en;
    private String nombre_periodo_fr;

    public void cambiarIdioma(String lang){
        switch (lang.toLowerCase()) {
            case "en":
                this.nombre_periodo = this.nombre_periodo_en;
                break;
            case "fr":
                this.nombre_periodo = this.nombre_periodo_fr;
                break;
        }
    }
}
