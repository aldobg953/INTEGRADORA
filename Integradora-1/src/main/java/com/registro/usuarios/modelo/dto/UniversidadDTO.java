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
    private String tipo_institucion; //publica o privada
    private String caracteristicas;  
    private MultipartFile logo;
    private MultipartFile portada;
    private MultipartFile imagen1;
    private Long id_u_traduccion;
    private String lang;
    private byte[] logoBytes;
    private byte[] portadaBytes;
    private byte[] imagen1Bytes;
    
    public UniversidadDTO(Long id_universidad, String nombre_completo, String nombre_abreviado, String pagina_web,
            String correo, String telefono, String informacion, String direccion, String direccionGoogle,
            String tipo_institucion, String caracteristicas) {
        this.id_universidad = id_universidad;
        this.nombre_completo = nombre_completo;
        this.nombre_abreviado = nombre_abreviado;
        this.pagina_web = pagina_web;
        this.correo = correo;
        this.telefono = telefono;
        this.informacion = informacion;
        this.direccion = direccion;
        this.direccionGoogle = direccionGoogle;
        this.tipo_institucion = tipo_institucion;
        this.caracteristicas = caracteristicas;
    }

    public UniversidadDTO() {
    }
    
    
}
