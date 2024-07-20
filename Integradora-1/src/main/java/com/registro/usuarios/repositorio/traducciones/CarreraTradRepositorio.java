package com.registro.usuarios.repositorio.traducciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.traducciones.CarreraTraduccion;

@Repository
public interface CarreraTradRepositorio extends JpaRepository<CarreraTraduccion,Long>{

    @Query("SELECT ct FROM CarreraTraduccion ct WHERE ct.carrera.universidad.id = :idCarrera AND ct.lang = :lang")
    List<CarreraTraduccion> findByUniversidadIdAndLang(@Param("idCarrera") Long idCarrera, @Param("lang") String lang);

    @Query("SELECT ct FROM CarreraTraduccion ct WHERE ct.carrera.id = :idCarrera AND ct.lang = :lang")
    List<CarreraTraduccion> findByCarreraIdAndLang(@Param("idCarrera") Long idCarrera, @Param("lang") String lang);

    
}
