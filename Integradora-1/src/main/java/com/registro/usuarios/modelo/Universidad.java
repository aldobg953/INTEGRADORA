package com.registro.usuarios.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "universidades")
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_universidad;
    private String nombre_completo;
    private String nombre_abreviado;
    private String pagina_web;
    private String correo;
    private String telefono;

    @Column(length = 600)
    private String informacion;
    private String direccion;

    @Column(length = 600)
    private String direccionGoogle; 
    private String tipo_institucion; //publica o privada
    @Column(length = 600)
    private String caracteristicas;

    public Universidad(Long id_universidad, String nombre_completo, String nombre_abreviado, String pagina_web,
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

    public Universidad(String nombre_completo, String nombre_abreviado, String pagina_web, String correo,
            String telefono, String informacion, String direccion, String direccionGoogle, String tipo_institucion,
            String caracteristicas) {
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

    public Universidad() {
    }  
    
}
