package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Foro;

@Repository
public interface ForoRepositorio extends JpaRepository<Foro,Long>{
    
    @Query(value = "SELECT * FROM foro WHERE fk_carrera = :carrera", nativeQuery = true)
    public List<Foro> findByCarrera(long carrera);
}
