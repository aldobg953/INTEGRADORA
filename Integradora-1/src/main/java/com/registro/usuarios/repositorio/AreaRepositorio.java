package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Area;

@Repository
public interface AreaRepositorio extends JpaRepository<Area,Long> {
    
}
