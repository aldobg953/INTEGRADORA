package com.registro.usuarios.modelo;

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
    private String informacion;
    private String direccion;
    private String direccionGoogle; 
    private String folder;   
}
