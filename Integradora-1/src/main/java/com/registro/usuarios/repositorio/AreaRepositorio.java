package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Area;

@Repository
public interface AreaRepositorio extends JpaRepository<Area,Long> {
    
    @Query(value = "SELECT * FROM areas WHERE " +
               "LOWER(nombre_area) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
               "OR LOWER(nombre_area_frances) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
               "OR LOWER(nombre_area_ingles) LIKE LOWER(CONCAT('%', :searchTerm, '%'))", 
       nativeQuery = true)
    List<Area> findByNombreContainingInAnyLanguage(@Param("searchTerm") String searchTerm);

}
