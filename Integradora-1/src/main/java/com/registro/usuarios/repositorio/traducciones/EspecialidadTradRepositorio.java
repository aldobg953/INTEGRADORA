package com.registro.usuarios.repositorio.traducciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.traducciones.EspecialidadTraduccion;

@Repository
public interface EspecialidadTradRepositorio extends JpaRepository<EspecialidadTraduccion,Long>{
    @Query("SELECT et FROM EspecialidadTraduccion et WHERE et.especialidad.id = :idEspecialidad AND et.lang = :lang")
    List<EspecialidadTraduccion> findByEspecialidadIdAndLang(@Param("idEspecialidad") Long idEspecialidad, @Param("lang") String lang);
}
