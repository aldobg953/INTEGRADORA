package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Rol;

@Repository
public interface RolRespository extends JpaRepository<Rol, Long>{

}
