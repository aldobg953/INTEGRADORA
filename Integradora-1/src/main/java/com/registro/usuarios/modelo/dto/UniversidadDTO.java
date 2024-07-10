package com.registro.usuarios.modelo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UniversidadDTO {
    
    private Long id_universidad;
    private String nombre_completo;
    private String nombre_abreviado;
    private String pagina_web;
    private String correo;
    private String telefono;
    private String informacion;
    private String direccion;
    private String direccionGoogle; 
    private String folder;
    private String tipo_institucion; //publica o privada
    private String caracteristicas;  
    private MultipartFile logo;
    private MultipartFile portada;
    private MultipartFile imagen1;


}
