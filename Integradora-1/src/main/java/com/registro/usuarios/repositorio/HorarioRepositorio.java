package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.usuarios.modelo.Horario;

@Repository
public interface HorarioRepositorio extends JpaRepository<Horario,Long>{
    
}
