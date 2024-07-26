package com.registro.usuarios.servicio.componentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.registro.usuarios.servicio.UsuarioServicio;

@Component
public class DesbloqueoAutomaticoJob {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Scheduled(fixedRate = 60000)
    public void desbloquearUsuarios() {
        usuarioServicio.desbloquearUsuarios();
    }
}
