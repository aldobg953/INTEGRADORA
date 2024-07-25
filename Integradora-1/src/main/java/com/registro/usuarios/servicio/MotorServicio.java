package com.registro.usuarios.servicio;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.dto.MotorResultados;
import com.registro.usuarios.repositorio.AreaRepositorio;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.UniversidadRepositorio;

@Service
public class MotorServicio {
    private final CarreraRepositorio carreraRepositorio;
    private final AreaRepositorio areaRepositorio;
    private final UniversidadRepositorio universidadRepositorio;
    private final CarreraServicio carreraServicio;

    public MotorServicio(CarreraRepositorio carreraRepositorio, 
                         AreaRepositorio areaRepositorio, 
                         UniversidadRepositorio universidadRepositorio,
                         CarreraServicio carreraServicio) {
        this.carreraRepositorio = carreraRepositorio;
        this.areaRepositorio = areaRepositorio;
        this.universidadRepositorio = universidadRepositorio;
        this.carreraServicio = carreraServicio;
    }

    public List<MotorResultados> searchAll(String searchTerm, String lang) {
        List<MotorResultados> results = new ArrayList<>();
        
        carreraRepositorio.findByNombreContainingIgnoreCase(searchTerm).forEach(carrera -> { 
            Carrera carreraTraducida = carreraServicio.aplicarTraduccion(carrera, lang);
            results.add(new MotorResultados(carreraTraducida.getId_carrera(), carreraTraducida.getNombre(), "carrera"));});
        
        areaRepositorio.findByNombreContainingInAnyLanguage(searchTerm).forEach(area -> {
            area.cambiarIdioma(lang);
            results.add(new MotorResultados(area.getId_area(), area.getNombre_area(), "area"));});
        
            universidadRepositorio.findByNombreContainingIgnoreCase(searchTerm).forEach(universidad -> 
            results.add(new MotorResultados(universidad.getId_universidad(), universidad.getNombre_completo(), "universidad")));
        
        return results;
    }
}
