package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Especialidad;

@Repository
public interface EspecialidadRepositorio extends JpaRepository<Especialidad,Long>{
    
}
