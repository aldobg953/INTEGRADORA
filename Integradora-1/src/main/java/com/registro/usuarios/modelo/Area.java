package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//almacena las ditintas areas de carreras, ejempo: humanidades, artes, salud, etc.
@Data
@Entity
@Table(name = "areas")
public class Area {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_area;
    private String nombre_area;
    private String descripcion;

     
    private String nombre_area_ingles;
    private String descripcion_ingles;
    
    private String nombre_area_frances;
    private String descripcion_frances;

    public String getNombreSegunIdioma(String lang) {
        switch (lang.toLowerCase()) {
            case "en":
                return nombre_area_ingles;
            case "fr":
                return nombre_area_frances;
            default:
                return nombre_area;
        }
    }

    public String getDescripcionSegunIdioma(String lang) {
        switch (lang.toLowerCase()) {
            case "en":
                return descripcion_ingles;
            case "fr":
                return descripcion_frances;
            default:
                return descripcion;
        }
    }

    public void cambiarIdioma(String lang){
        switch (lang.toLowerCase()) {
            case "en":
                this.nombre_area = this.nombre_area_ingles;
                break;
            case "fr":
                this.nombre_area = this.nombre_area_ingles;
                break;
        }
    }
}
