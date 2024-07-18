package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//En esta clase se almacenan los tipos de horarios a filtrar: matutino, vespertino, nocturno o hibrido.
@Data
@Entity
@Table(name = "Horarios")
public class Horario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;
    private String nombre_horario;
    private String nombre_horario_en;
    private String nombre_horario_fr;

    public void cambiarIdioma(String lang){
        switch (lang.toLowerCase()) {
            case "en":
                this.nombre_horario = this.nombre_horario_en;
                break;
            case "fr":
                this.nombre_horario = this.nombre_horario_fr;
                break;
        }
    }
}
