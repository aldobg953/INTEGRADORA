package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.modelo.Foro;

@Repository
public interface ForoRepositorio extends JpaRepository<Foro,Long>{
    
    @Query(value = "SELECT * FROM foro WHERE fk_carrera = :carrera", nativeQuery = true)
    public List<Foro> findByCarrera(long carrera);

    @Transactional
    @Modifying
    @Query(value = "UPDATE foro SET calificacion = :calificacionNueva WHERE fk_usuario = :usuario", nativeQuery = true)
    public void actualizarCalificacion(@Param("usuario") long usuario, @Param("calificacionNueva") int calificacionNueva);

    @Query(value = "SELECT round(avg(calificacion)) FROM foro WHERE fk_carrera = :carreraId GROUP BY fk_usuario", nativeQuery = true)
    List<Integer> obtenerCalificacionesCarrera(@Param("carreraId") Long carreraId);
}
