package com.registro.usuarios.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Universidad;

@Repository
public interface UniversidadRepositorio extends JpaRepository<Universidad,Long>{

    @Query("SELECT u FROM Universidad u LEFT JOIN FETCH u.traducciones t WHERE u.id_universidad = :id AND t.lang = :lang")
    Optional<Universidad> findByIdWithTranslations(@Param("id") Long id, @Param("lang") String lang);

    @Query(value = "SELECT * FROM universidades WHERE " +
               "LOWER(nombre_completo) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
               "OR LOWER(nombre_abreviado) LIKE LOWER(CONCAT('%', :searchTerm, '%'))", 
       nativeQuery = true)
    List<Universidad> findByNombreContainingIgnoreCase(@Param("searchTerm") String searchTerm);

}