package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.PeriodoEscolar;

@Repository
public interface PeriodoEscolarRepositorio extends JpaRepository<PeriodoEscolar,Long>{
    
}
