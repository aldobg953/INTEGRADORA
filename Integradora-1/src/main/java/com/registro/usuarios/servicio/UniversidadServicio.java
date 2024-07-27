package com.registro.usuarios.servicio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.dto.UniversidadDTO;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
import com.registro.usuarios.modelo.traducciones.UniversidadTraduccion;
import com.registro.usuarios.repositorio.UniversidadRepositorio;
import com.registro.usuarios.repositorio.traducciones.UniversidadTradRepositorio;

@Service
public class UniversidadServicio {
    
    @Autowired
    UniversidadRepositorio universidadRepositorio;

    @Autowired
    UniversidadTradRepositorio universidadTradRepositorio;

    @Value("${file.upload-dir}")
    private String uploadDir;


    private Universidad aplicarTraduccion(Universidad universidad, String lang) {
        UniversidadTraduccion traduccion = universidad.getTraducciones().stream()
            .filter(t -> t.getLang().equals(lang))
            .findFirst()
            .orElse(null);

        if (traduccion != null) {
            universidad.setInformacion(traduccion.getInformacion());
            universidad.setCaracteristicas(traduccion.getCaracteristicas());
            universidad.setTipo_institucion(traduccion.getTipo_institucion());
        }

        return universidad;
    }

    public Universidad getUniversidadById(Long id, String lang) {
        Universidad universidad = universidadRepositorio.getById(id);
        return aplicarTraduccion(universidad, lang);
    }

    public List<Universidad> getAllUniversidades(String lang) {
        return universidadRepositorio.findAll().stream().map(universidad -> aplicarTraduccion(universidad, lang))
        .collect(Collectors.toList());
    }

    public List<UniversidadResumen> getAllUniversidadResumen(Collection<Rol> roles, Long id_universidad) {
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
    
        return esSuperUsuario ? getAllUniversidadesResumen() : getUniversidadResumenById(id_universidad);
    }
    
    public List<UniversidadResumen> getAllUniversidadesResumen() {
        return universidadRepositorio.findAll().stream()
                .map(this::convertirAResumen)
                .collect(Collectors.toList());
    }
    
    private List<UniversidadResumen> getUniversidadResumenById(Long id_universidad) {
        Universidad universidad = universidadRepositorio.getById(id_universidad);
        return Collections.singletonList(convertirAResumen(universidad));
    }
    
    private UniversidadResumen convertirAResumen(Universidad universidad) {
        UniversidadResumen resumen = new UniversidadResumen();
        resumen.setId_universidad(universidad.getId_universidad());
        resumen.setNombre_completo(universidad.getNombre_completo());
        return resumen;
    }
    
    public Universidad guardarUniversidad(UniversidadDTO universidadDTO){
        try {
            Universidad universidad = new Universidad(universidadDTO.getId_universidad(), universidadDTO.getNombre_completo(),universidadDTO.getNombre_abreviado(),
            universidadDTO.getPagina_web(),universidadDTO.getCorreo(),universidadDTO.getTelefono(),universidadDTO.getInformacion(),
            universidadDTO.getDireccion(), universidadDTO.getDireccionGoogle(),universidadDTO.getTipo_institucion(), 
            universidadDTO.getCaracteristicas());
            return universidadRepositorio.save(universidad);
        } catch (Exception e) {
            return null;
        }
    }

    public UniversidadDTO getUniversidadDTO(Long id){
        Universidad  universidad = universidadRepositorio.getById(id);
        return new UniversidadDTO(universidad.getId_universidad(),universidad.getNombre_completo(),
        universidad.getNombre_abreviado(),universidad.getPagina_web(),universidad.getCorreo(),universidad.getTelefono(),
        universidad.getInformacion(),universidad.getDireccion(),universidad.getDireccionGoogle(),universidad.getTipo_institucion(),
        universidad.getCaracteristicas());
    }

    public boolean eliminarUniversidad(Long id){
        try {
            Universidad universidad = universidadRepositorio.getById(id);
            Path folder = Paths.get(uploadDir+"/"+universidad.getNombre_abreviado());
            deleteDirectoryRecursively(folder);
            universidadRepositorio.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void deleteDirectoryRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path entry : directoryStream) {
                    deleteDirectoryRecursively(entry);
                }
            }
        }
        Files.delete(path);
    }

    public UniversidadDTO getUniTraduccion(Long id_universidad, String lang){
        List<UniversidadTraduccion> universidadTraduccionList =  universidadTradRepositorio.findByUniversidadIdAndLang(id_universidad, lang);
        UniversidadDTO uniDAOTaduccion = new UniversidadDTO();
        uniDAOTaduccion.setId_universidad(id_universidad);
        uniDAOTaduccion.setLang(lang);
        if(!universidadTraduccionList.isEmpty()){
            UniversidadTraduccion universidadTraduccion = universidadTraduccionList.get(0);
            uniDAOTaduccion.setId_u_traduccion(universidadTraduccion.getId_u_traduccion());
            uniDAOTaduccion.setCaracteristicas(universidadTraduccion.getCaracteristicas());
            uniDAOTaduccion.setInformacion(universidadTraduccion.getInformacion());
            uniDAOTaduccion.setTipo_institucion(universidadTraduccion.getTipo_institucion());
        }
        return uniDAOTaduccion;
    }

    public boolean guardarTraduccionUni(UniversidadDTO universidadDTO){
        UniversidadTraduccion universidadTraduccion = new UniversidadTraduccion(universidadDTO.getId_u_traduccion(), 
        universidadRepositorio.getById(universidadDTO.getId_universidad()), universidadDTO.getLang(),
        universidadDTO.getCaracteristicas(), universidadDTO.getInformacion(), universidadDTO.getTipo_institucion());
        
        try {
            universidadTradRepositorio.save(universidadTraduccion);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean existeUniversidad(String nombre_abreviado){
        Universidad universidad = universidadRepositorio.findByNombre_abreviado(nombre_abreviado);
        if(universidad!=null){
            return true;
        }else{
            return false;
        }
    }
    
}
