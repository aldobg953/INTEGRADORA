package com.registro.usuarios.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.usuarios.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

   @PostMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquearUsuario(@PathVariable Long id) {
        usuarioServicio.bloquearUsuario(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario bloqueado exitosamente");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/desbloquear")
    public ResponseEntity<?> desbloquearUsuario(@PathVariable Long id) {
        usuarioServicio.desbloquearUsuarioInd(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario desbloqueado exitosamente");
        return ResponseEntity.ok(response);
    }
}
