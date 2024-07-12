package com.registro.usuarios.servicio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Rol;
import com.registro.usuarios.modelo.Universidad;
import com.registro.usuarios.modelo.dto.UniversidadDTO;
import com.registro.usuarios.modelo.resumen.UniversidadResumen;
import com.registro.usuarios.repositorio.UniversidadRepositorio;

@Service
public class UniversidadServicio {
    
    @Autowired
    UniversidadRepositorio universidadRepositorio;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Optional<Universidad> getUniversidadesById(Long id){
        return universidadRepositorio.findById(id);
    }

    public List<Universidad> getAllUniversidades(){
        return universidadRepositorio.findAll();
    }

    public List<UniversidadResumen> getAllUniversidadResumen(Collection<Rol> roles, Long id_universidad) {
        boolean esSuperUsuario = roles.stream().anyMatch(rol -> rol.getId_rol() == 3);
    
        return esSuperUsuario ? getAllUniversidadesResumen() : getUniversidadResumenById(id_universidad);
    }
    
    private List<UniversidadResumen> getAllUniversidadesResumen() {
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
    

    public boolean guardarUniversidad(UniversidadDTO universidadDTO){
        try {
            Universidad universidad = new Universidad(universidadDTO.getId_universidad(), universidadDTO.getNombre_completo(),universidadDTO.getNombre_abreviado(),
            universidadDTO.getPagina_web(),universidadDTO.getCorreo(),universidadDTO.getTelefono(),universidadDTO.getInformacion(),
            universidadDTO.getDireccion(), universidadDTO.getDireccionGoogle(),universidadDTO.getTipo_institucion(), 
            universidadDTO.getCaracteristicas());
            universidadRepositorio.save(universidad);
        } catch (Exception e) {
            return false;
        }
        return true;
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
}
