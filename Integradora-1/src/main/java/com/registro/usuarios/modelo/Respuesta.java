package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_respuesta;
    private String text;
    private String text_ingles;
    private String text_frances;
    
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    private Long id_area;  

    public void cambiarIdioma(String lang){
        if(lang.equals("en")){
            this.text=this.text_ingles;
        }
        if(lang.equals("fr")){
            this.text=this.text_frances;
        }
    }

    public Respuesta() {
    }

    public Respuesta(Long id_respuesta, String text, String text_ingles, String text_frances, Pregunta pregunta,
            Long id_area) {
        this.id_respuesta = id_respuesta;
        this.text = text;
        this.text_ingles = text_ingles;
        this.text_frances = text_frances;
        this.pregunta = pregunta;
        this.id_area = id_area;
    }

    public Respuesta(String text, String text_ingles, String text_frances, Pregunta pregunta, Long id_area) {
        this.text = text;
        this.text_ingles = text_ingles;
        this.text_frances = text_frances;
        this.pregunta = pregunta;
        this.id_area = id_area;
    }
    
}
