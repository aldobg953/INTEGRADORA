package com.registro.usuarios.servicio;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Carrera;
import com.registro.usuarios.modelo.Especialidad;
import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.dto.EspecialidadDTO;
import com.registro.usuarios.modelo.traducciones.EspecialidadTraduccion;
import com.registro.usuarios.repositorio.CarreraRepositorio;
import com.registro.usuarios.repositorio.EspecialidadRepositorio;
import com.registro.usuarios.repositorio.HorarioRepositorio;
import com.registro.usuarios.repositorio.ModalidadRepositorio;
import com.registro.usuarios.repositorio.PeriodoEscolarRepositorio;
import com.registro.usuarios.repositorio.traducciones.EspecialidadTradRepositorio;

@Service
public class EspecialidadServicio {
    
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    private EspecialidadTradRepositorio especialidadTradRepositorio;

    @Autowired
    private CarreraServicio carreraServicio;
    
    @Autowired
    private ModalidadRepositorio modalidadRepositorio;
    @Autowired
    private PeriodoEscolarRepositorio periodoEscolarRepositorio;
    @Autowired
    private HorarioRepositorio horarioRepositorio;
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    public List<Especialidad> getAllEspecilidades(){
        return especialidadRepositorio.findAll();
    }

    public Especialidad getEspecialidadById(Long id, String lang){
        if(!lang.equals("es")){
            Especialidad especialidad = aplicarTraduccion(especialidadRepositorio.findById(id).get(), lang);
            especialidad.setCarrera(carreraServicio.aplicarTraduccion(especialidad.getCarrera(), lang));
            return especialidad;
        }else{
            return especialidadRepositorio.findById(id).get();
        }
       
    }

    public Especialidad guardarEspecialidad(Especialidad especialidad){
        return especialidadRepositorio.save(especialidad); 
    }

    public Especialidad guardarEspecialidad(EspecialidadDTO especialidadDto){
        try {
            System.out.println(especialidadDto.getNombre() + "\n\n\n\n");
            Especialidad especialidad = new Especialidad(especialidadDto.getId_especialidad(),especialidadDto.getNombre(),
            especialidadDto.getDescripcion_breve(),especialidadDto.getInformacion(),especialidadDto.getRoadmap(),
            especialidadDto.getCosto(),especialidadDto.getHorario_especifico(),especialidadDto.isBilingue(),
            carreraRepositorio.getById(especialidadDto.getCarrera()),modalidadRepositorio.getById(especialidadDto.getModalidad()),
            periodoEscolarRepositorio.getById(especialidadDto.getPeriodoEscolar()),horarioRepositorio.getById(especialidadDto.getHorario()));
            System.out.println(especialidad.getNombre() + "\n\n\n\n");
            return especialidadRepositorio.save(especialidad); 
        } catch (Exception e) {
            return null;
        }
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

    public List<Especialidad> getEspecialidadesbyUsuario(Collection<Rol> roles, Long id_universidad, String lang){
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
        if(esSuperUsuario){
            return getAllEspecilidades().stream().map(especialidad -> {
                Carrera carreraTraducida = carreraServicio.aplicarTraduccion(especialidad.getCarrera(), lang);
                especialidad.setCarrera(carreraTraducida);
                return especialidad;
            }).collect(Collectors.toList());
        }else{
            return getAllEspecilidades().stream().filter(especialidad -> especialidad.getCarrera().getUniversidad().getId_universidad().equals(id_universidad))
            .map(especialidad -> {
                Carrera carreraTraducida = carreraServicio.aplicarTraduccion(especialidad.getCarrera(), lang);
                especialidad.setCarrera(carreraTraducida);
                return especialidad;
            })
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
            especialidad.setHorario_especifico(traduccion.getHorario_especifico());
        }

        return especialidad;
    }

    public Especialidad getEspecialidadTraduccion(Long id_especialidad, String lang){
        List<EspecialidadTraduccion> especialidadTraduccionList = especialidadTradRepositorio.findByEspecialidadIdAndLang(id_especialidad, lang);
        Especialidad especialidadDTO = new Especialidad();
        especialidadDTO.setId_especialidad(id_especialidad);
        especialidadDTO.setLang(lang);
        if(!especialidadTraduccionList.isEmpty()){
            EspecialidadTraduccion especialidadTraduccion = especialidadTraduccionList.get(0);
            especialidadDTO.setId_e_traduccion(especialidadTraduccion.getId_e_traduccion());
            especialidadDTO.setNombre(especialidadTraduccion.getNombre());
            especialidadDTO.setDescripcion_breve(especialidadTraduccion.getDescripcion_breve());
            especialidadDTO.setInformacion(especialidadTraduccion.getInformacion());
            especialidadDTO.setHorario_especifico(especialidadTraduccion.getHorario_especifico());
        }
        return especialidadDTO;
    }

    public boolean guardarTraduccionEsp(Especialidad especialidad){
        EspecialidadTraduccion especialidadTraduccion = new EspecialidadTraduccion(especialidad.getId_e_traduccion(),
        especialidad ,especialidad.getLang(),especialidad.getNombre(),
        especialidad.getDescripcion_breve(),especialidad.getInformacion(), especialidad.getHorario_especifico());
        try {
            especialidadTradRepositorio.save(especialidadTraduccion);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public EspecialidadDTO getEspecialidadDTO(Long id, String lang) {
        Especialidad especialidad = aplicarTraduccion(especialidadRepositorio.getById(id), lang);
        return convertirADTO(especialidad);
        
    }

    public static EspecialidadDTO convertirADTO(Especialidad especialidad) {
        EspecialidadDTO dto = new EspecialidadDTO();
        
        dto.setId_especialidad(especialidad.getId_especialidad());
        dto.setNombre(especialidad.getNombre());
        dto.setDescripcion_breve(especialidad.getDescripcion_breve());
        dto.setInformacion(especialidad.getInformacion());
        dto.setRoadmap(especialidad.getRoadmap());
        dto.setCosto(especialidad.getCosto());
        dto.setHorario_especifico(especialidad.getHorario_especifico());
        dto.setBilingue(especialidad.isBilingue());
        dto.setId_e_traduccion(especialidad.getId_e_traduccion());
        dto.setLang(especialidad.getLang());
        
        // Manejar las relaciones
        if (especialidad.getCarrera() != null) {
            dto.setCarrera(especialidad.getCarrera().getId_carrera());
        }
        
        if (especialidad.getModalidad() != null) {
            dto.setModalidad(especialidad.getModalidad().getId_modalidad());
        }
        
        if (especialidad.getPeriodoEscolar() != null) {
            dto.setPeriodoEscolar(especialidad.getPeriodoEscolar().getId_periodo_escolar());
        }
        
        if (especialidad.getHorario() != null) {
            dto.setHorario(especialidad.getHorario().getId_horario());
        }
        
        return dto;
    }

    private Especialidad aplicarTraduccion(Especialidad especialidad, String lang) {
        EspecialidadTraduccion traduccion = especialidad.getTraducciones().stream()
            .filter(t -> t.getLang().equals(lang))
            .findFirst()
            .orElse(null);
    
        if (traduccion != null) {
            // Aplicar traducciones a los campos de Especialidad
            especialidad.getPeriodoEscolar().cambiarIdioma(lang);
            especialidad.setNombre(traduccion.getNombre());
            especialidad.setDescripcion_breve(traduccion.getDescripcion_breve());
            especialidad.setInformacion(traduccion.getInformacion());
            especialidad.setLang(lang);
            especialidad.setId_e_traduccion(traduccion.getId_e_traduccion());
            especialidad.setHorario_especifico(traduccion.getHorario_especifico());
        }
    
        return especialidad;
    }
}


