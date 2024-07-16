package com.registro.usuarios.servicio;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.traducciones.EspecialidadTraduccion;
import com.registro.usuarios.repositorio.EspecialidadRepositorio;

@Service
public class EspecialidadServicio {
    
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

    public List<Especialidad> getAllEspecilidades(){
        return especialidadRepositorio.findAll();
    }

    public Optional<Especialidad> getEspecialidadById(Long id){
        return especialidadRepositorio.findById(id);
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad){
        return especialidadRepositorio.save(especialidad);
        
    }

    public List<Especialidad> getEspecialidadByCarrera(Long carrera, String lang){
        List<Especialidad> especialidades = especialidadRepositorio.findbyCarrera(carrera);
        if(lang.equals("es")){
            return especialidades;
        }else{
            return especialidades.stream().map(especialidad -> aplicarTraduccionEsp(especialidad, lang)).
            collect(Collectors.toList());
        }
        
    }

    public List<Especialidad> getEspecialidadesbyUsuario(Collection<Rol> roles, Long id_universidad){
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
        if(esSuperUsuario){
            return getAllEspecilidades();
        }else{
            return getAllEspecilidades().stream().filter(especialidad -> especialidad.getCarrera().getUniversidad().getId_universidad().equals(id_universidad))
            .collect(Collectors.toList());
        }
    }


    public boolean deleteEspecialidad(Long id){
        try {
            especialidadRepositorio.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

     private Especialidad aplicarTraduccionEsp(Especialidad especialidad, String lang) {
        EspecialidadTraduccion traduccion = especialidad.getTraducciones().stream()
            .filter(t -> t.getLang().equals(lang))
            .findFirst()
            .orElse(null);

        if (traduccion != null) {
            especialidad.setNombre(traduccion.getNombre());
            especialidad.setInformacion(traduccion.getInformacion());
            especialidad.setDescripcion_breve(traduccion.getDescripcion_breve());
        }

        return especialidad;
    }
}
