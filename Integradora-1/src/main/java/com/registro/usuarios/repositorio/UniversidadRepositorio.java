package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Universidad;

@Repository
public interface UniversidadRepositorio extends JpaRepository<Universidad,Long>{

}
