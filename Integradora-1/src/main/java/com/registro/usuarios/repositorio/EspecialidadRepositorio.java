package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.registro.usuarios.modelo.Especialidad;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad,Long>{
    
    @Query(value = "SELECT * FROM especialidades WHERE fk_carrera = :carrera", nativeQuery = true)
    public List<Especialidad> findbyCarrera(long carrera);
}
