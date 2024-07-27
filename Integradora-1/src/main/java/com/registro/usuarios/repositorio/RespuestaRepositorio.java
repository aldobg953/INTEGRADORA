package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro.usuarios.modelo.Respuesta;

public interface RespuestaRepositorio extends JpaRepository<Respuesta,Long>{
    
}
