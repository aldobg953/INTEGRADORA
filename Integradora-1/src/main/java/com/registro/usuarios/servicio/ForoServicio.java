package com.registro.usuarios.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.EspecialidadRepositorio;
import com.registro.usuarios.repositorio.ForoRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

@Service
public class ForoServicio {
    @Autowired
    private ForoRepositorio foroRepositorio;

    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Foro crearForoCarrera(Long carreraId, Long especialidadId, String email, String comentario) {
        Foro foro = new Foro();
        foro.setComentario(comentario);

        // Recuperar entidades relacionadas por sus IDs
        /*Especialidad especialidad = especialidadRepository.findById(especialidadId)
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad not found"));*/
        Usuario usuario = usuarioRepositorio.findByEmail(email);

        foro.setFk_carrera(carreraId);
        //foro.setEspecialidad(especialidad);
        foro.setUsuario(usuario);

        return foroRepositorio.save(foro);
    }
}
