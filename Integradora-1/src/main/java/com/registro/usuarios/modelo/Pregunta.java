package com.registro.usuarios.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pregunta;
    private String text;
    private String text_ingles;
    private String text_frances;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    public void cambiarIdioma(String lang){
        if(lang.equals("en")){
            this.text=this.text_ingles;
        }
        if(lang.equals("fr")){
            this.text=this.text_frances;
        }
    }

    public Pregunta() {
    }

    public Pregunta(Long id_pregunta, String text, String text_ingles, String text_frances,
            List<Respuesta> respuestas) {
        this.id_pregunta = id_pregunta;
        this.text = text;
        this.text_ingles = text_ingles;
        this.text_frances = text_frances;
        this.respuestas = respuestas;
    }

    public Pregunta(String text, String text_ingles, String text_frances, List<Respuesta> respuestas) {
        this.text = text;
        this.text_ingles = text_ingles;
        this.text_frances = text_frances;
        this.respuestas = respuestas;
    }
    
}
