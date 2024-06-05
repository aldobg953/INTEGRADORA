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
    private Long id;
    private String nombre;
    private String informacion;
    private String calle;
    private String colonia;
    private String numeroExterior;
    private String codigoPostal;
    private String ciudad;
    private String estado;
    private String pais;
    private String direccionGoogle;    
}
