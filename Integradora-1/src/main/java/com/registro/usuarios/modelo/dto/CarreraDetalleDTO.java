package com.registro.usuarios.modelo.dto;

import java.util.Base64;

import com.registro.usuarios.modelo.Carrera;

import lombok.Data;

@Data
public class CarreraDetalleDTO {
    private Carrera carrera;
    private String imagen;
    private String logoUni;

    public CarreraDetalleDTO(Carrera carrera) {
        if (carrera != null) {
            this.setImagenBytes(carrera.getImagenBytes());
            this.carrera = carrera;
            this.setLogoBytes(carrera.getUniversidad().getLogoBytes());
        }
    }

    private void setImagenBytes(byte[] imagenBytes) {
        this.imagen = convertToBase64(imagenBytes);
    }

    private void setLogoBytes(byte[] imagenBytes) {
        this.logoUni = convertToBase64(imagenBytes);
    }

    private String convertToBase64(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        return null;
    }

}
