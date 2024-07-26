package com.registro.usuarios.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.registro.usuarios.modelo.Area;
import com.registro.usuarios.modelo.dto.AreaDTO;
import com.registro.usuarios.repositorio.AreaRepositorio;

@Service
public class AreaServicio {
    
    @Autowired
    private AreaRepositorio areaRepositorio;

    public List<AreaDTO> getAllAreas(String lang){

        return convertirAAreaDTOList(areaRepositorio.findAll(),lang);
    }

    public List<AreaDTO> convertirAAreaDTOList(List<Area> areas, String lang) {
        return areas.stream()
                .map(area -> convertirAAreaDTO(area, lang))
                .collect(Collectors.toList());
    }

    private AreaDTO convertirAAreaDTO(Area area, String lang) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId_area(area.getId_area());
        areaDTO.setNombre_area(area.getNombreSegunIdioma(lang));
        areaDTO.setDescripcion(area.getDescripcionSegunIdioma(lang));
        return areaDTO;
    }

    public Area getAreaByid(Long id){
        return areaRepositorio.getById(id);
    }
}
