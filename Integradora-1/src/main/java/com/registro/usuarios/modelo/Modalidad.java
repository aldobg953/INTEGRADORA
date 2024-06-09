package com.registro.usuarios.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//Esta clase nos da la modalidad de las carreras y especialidades, es decir, presencial, virtual, hibrido, precencial o virtual
@Data
@Entity
@Table(name = "modalidades")
public class Modalidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_modalidad;
    private String nombre_modalidad;

}
