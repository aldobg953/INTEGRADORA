package com.registro.usuarios.test;


public class Respuestas {
    private int idRespuesta;
    private int idPregunta;
    private String respuesta;
    private int valor;
    public Respuestas(int idRespuesta, String respuesta, int valor, int idPregunta) {
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
        this.valor = valor;
        this.idPregunta = idPregunta;
    }
    public int getIdRespuesta() {
        return idRespuesta;
    }
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getIdPregunta() {
        return idPregunta;
    }
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
    
    
}
