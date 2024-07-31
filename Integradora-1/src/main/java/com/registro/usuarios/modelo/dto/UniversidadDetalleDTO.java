package com.registro.usuarios.modelo.dto;

import java.util.Base64;

import com.registro.usuarios.modelo.Universidad;

public class UniversidadDetalleDTO {
    private Universidad universidad;
    private String logoBase64;
    private String portadaBase64;
    private String imagen1Base64;

    public UniversidadDetalleDTO(Universidad universidad) {
        if (universidad != null) {
            this.setLogoBytes(universidad.getLogoBytes());
            this.setPortadaBytes(universidad.getPortadaBytes());
            this.setImagen1Bytes(universidad.getImagen1Bytes());
            this.universidad = universidad;
        }
    }
    
    private void setLogoBytes(byte[] logoBytes) {
        this.logoBase64 = convertToBase64(logoBytes);
    }

    private void setPortadaBytes(byte[] portadaBytes) {
        this.portadaBase64 = convertToBase64(portadaBytes);
    }

    private void setImagen1Bytes(byte[] imagen1Bytes) {
        this.imagen1Base64 = convertToBase64(imagen1Bytes);
    }

    private String convertToBase64(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        return null;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public String getPortadaBase64() {
        return portadaBase64;
    }

    public String getImagen1Base64() {
        return imagen1Base64;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }

    public void setPortadaBase64(String portadaBase64) {
        this.portadaBase64 = portadaBase64;
    }

    public void setImagen1Base64(String imagen1Base64) {
        this.imagen1Base64 = imagen1Base64;
    }

}
