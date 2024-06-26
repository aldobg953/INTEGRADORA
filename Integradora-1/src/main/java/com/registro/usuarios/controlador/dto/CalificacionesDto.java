package com.registro.usuarios.controlador.dto;

import lombok.Data;

@Data
public class CalificacionesDto {
    public float promedioGral;
    public int promCincoE;
    public int promCuatroE;
    public int promTresE;
    public int promDosE;
    public int promUnaE;
    public int cantidadCalif;
    
    public CalificacionesDto(float promedioGral, int promCincoE, int promCuatroE, int promTresE, int promDosE,
            int promUnaE, int cantidadCalif) {
        this.promedioGral = promedioGral;
        this.promCincoE = promCincoE;
        this.promCuatroE = promCuatroE;
        this.promTresE = promTresE;
        this.promDosE = promDosE;
        this.promUnaE = promUnaE;
        this.cantidadCalif = cantidadCalif;
    }

    
}
