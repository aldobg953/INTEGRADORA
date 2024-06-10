package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Carrera;

@Repository
public interface CarreraRepositorio extends JpaRepository<Carrera,Long>{
    
    @Query(value = "SELECT * FROM carreras WHERE fk_area = :area", nativeQuery = true)
    public List<Carrera> findByArea(long area);
    
    @Query(value = "SELECT * FROM carreras WHERE fk_universidad = :universidad", nativeQuery = true)
    public List<Carrera> findByUniversidad(long universidad);
}
