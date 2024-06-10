package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Modalidad;

@Repository
public interface ModalidadRepositorio extends JpaRepository<Modalidad,Long>{
    
}
