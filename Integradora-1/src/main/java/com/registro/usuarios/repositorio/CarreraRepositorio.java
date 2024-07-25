package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Carrera;

@Repository
public interface CarreraRepositorio extends JpaRepository<Carrera,Long>, JpaSpecificationExecutor<Carrera> {
    
    @Query(value = "SELECT * FROM carreras WHERE fk_area = :area", nativeQuery = true)
    public List<Carrera> findByArea(long area);
    
    @Query(value = "SELECT * FROM carreras WHERE fk_universidad = :universidad", nativeQuery = true)
    public List<Carrera> findByUniversidad(long universidad);

    @Query("SELECT c FROM Carrera c ORDER BY c.contador DESC")
    List<Carrera> findTop10ByOrderByContadorDesc(Pageable pageable);

    @Query(value = "SELECT c.* FROM carreras c " +
    "LEFT JOIN carrera_traduccion ct ON ct.fk_carrera = c.id_carrera " +
    "WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
    "OR LOWER(ct.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
    "GROUP BY c.id_carrera", 
    nativeQuery = true)
    List<Carrera> findByNombreContainingIgnoreCase(@Param("searchTerm") String searchTerm);


}
