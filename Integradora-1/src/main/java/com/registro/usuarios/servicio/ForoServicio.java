package com.registro.usuarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Foro;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.dto.CalificacionesDto;
import com.registro.usuarios.repositorio.ForoRepositorio;
import com.registro.usuarios.repositorio.UsuarioRepositorio;

@Service
public class ForoServicio {
    @Autowired
    private ForoRepositorio foroRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Foro crearForoCarrera(Long carreraId,String email, String comentario, int calificacion) {
        Foro foro = new Foro();
        foro.setComentario(comentario);
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        foro.setFk_carrera(carreraId);
        foro.setUsuario(usuario);
        foro.setCalificacion(calificacion);
        foroRepositorio.actualizarCalificacion(usuario.getId_usuario(), calificacion);
        return foroRepositorio.save(foro);
    }

    public CalificacionesDto obtenerCalifCarrera(Long idCarrera){
        List<Integer> calificaciones = foroRepositorio.obtenerCalificacionesCarrera(idCarrera);
        int cantidadCalif = calificaciones.size();
        int promCincoE = 0;
        int promCuatroE = 0;
        int promTresE = 0;
        int promDosE = 0;
        int promUnaE = 0;
        float promedioGral = 0;
        if(cantidadCalif !=0){
            for (Integer calif : calificaciones) {
                
                switch (calif) {
                    case 1:
                        promUnaE++;
                    break;
                    case 2:
                        promDosE++;
                    break;
                    case 3:
                        promTresE++;
                    break;
                    case 4:
                        promCuatroE++;
                    break;
                    case 5:
                        promCincoE++;
                    break;
                }
                promedioGral+=calif;
                
            }
            promCincoE = (promCincoE*100)/cantidadCalif;
            promCuatroE = (promCuatroE*100)/cantidadCalif;
            promTresE = (promTresE*100)/cantidadCalif;
            promDosE = (promDosE*100)/cantidadCalif;
            promUnaE = (promUnaE*100)/cantidadCalif;
            promedioGral /=cantidadCalif;
        }
        return new CalificacionesDto(promedioGral, promCincoE, promCuatroE, promTresE, promDosE, promUnaE, cantidadCalif);
    }
}
