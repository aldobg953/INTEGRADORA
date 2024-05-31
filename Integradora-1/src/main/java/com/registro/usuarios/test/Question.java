package com.registro.usuarios.test;

import java.util.List;

public class Question {
    private Long id;
    private String questionText;
    private List<Respuestas> respuestas;

    // Constructores, getters y setters
    public Question(Long id, String questionText, List<Respuestas> respuestas) {
        this.id = id;
        this.questionText = questionText;
        this.respuestas = respuestas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<Respuestas> getRespuestas() { return respuestas; }
    public void setOptions(List<Respuestas> respuestas) { this.respuestas = respuestas; }

}