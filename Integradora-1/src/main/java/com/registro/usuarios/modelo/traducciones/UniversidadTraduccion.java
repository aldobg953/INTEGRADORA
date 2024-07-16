package com.registro.usuarios.modelo.traducciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.registro.usuarios.modelo.Universidad;

import lombok.Data;

@Data
@Entity
@Table(name="universidad_traduccion")
public class UniversidadTraduccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_u_traduccion;
    @ManyToOne
    @JoinColumn(name = "id_universidad")
    private Universidad universidad;
    private String lang;
    @Column(length = 600)
    private String caracteristicas;
    @Column(length = 600)
    private String informacion;
    private String tipo_institucion;

    public UniversidadTraduccion(Long id_u_traduccion, Universidad universidad, String lang, String caracteristicas,
            String informacion, String tipo_institucion) {
        this.id_u_traduccion = id_u_traduccion;
        this.universidad = universidad;
        this.lang = lang;
        this.caracteristicas = caracteristicas;
        this.informacion = informacion;
        this.tipo_institucion = tipo_institucion;
    }

    public UniversidadTraduccion(Universidad universidad, String lang, String caracteristicas, String informacion,
            String tipo_institucion) {
        this.universidad = universidad;
        this.lang = lang;
        this.caracteristicas = caracteristicas;
        this.informacion = informacion;
        this.tipo_institucion = tipo_institucion;
    }

    public UniversidadTraduccion() {
    }

    
    
}
