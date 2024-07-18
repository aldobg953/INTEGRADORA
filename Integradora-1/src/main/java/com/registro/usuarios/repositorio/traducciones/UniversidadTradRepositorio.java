package com.registro.usuarios.repositorio.traducciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.traducciones.UniversidadTraduccion;

@Repository
public interface UniversidadTradRepositorio extends JpaRepository<UniversidadTraduccion,Long>{
    @Query("SELECT ut FROM UniversidadTraduccion ut WHERE ut.universidad.id = :idUniversidad AND ut.lang = :lang")
    List<UniversidadTraduccion> findByUniversidadIdAndLang(@Param("idUniversidad") Long idUniversidad, @Param("lang") String lang);
}
