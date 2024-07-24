package com.registro.usuarios.servicio;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

public class FavoritoService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @Transactional
    public void agregarFavorito(String email, Long carreraId) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        
        Carrera carrera = carreraRepositorio.findById(carreraId).get();
        
        usuario.getCarrerasFavoritas().add(carrera);
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void eliminarFavorito(String email, Long carreraId) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        
        Carrera carrera = carreraRepositorio.findById(carreraId).get();
        
        usuario.getCarrerasFavoritas().remove(carrera);
        usuarioRepositorio.save(usuario);
    }

    public Set<Carrera> obtenerFavoritos(String email) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        return usuario.getCarrerasFavoritas();
    }
}
