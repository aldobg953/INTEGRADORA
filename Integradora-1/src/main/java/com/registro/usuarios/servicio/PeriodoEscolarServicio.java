package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.PeriodoEscolar;
import com.registro.usuarios.repositorio.PeriodoEscolarRepositorio;

@Service
public class PeriodoEscolarServicio {
    
    @Autowired
    private PeriodoEscolarRepositorio periodoEscolarRepositorio;

    public List<PeriodoEscolar> getAllPeriodoEscolar(){
        return periodoEscolarRepositorio.findAll();
    }
}
