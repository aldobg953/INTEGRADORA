package com.registro.usuarios.controlador;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/perfil")
    public String mostrarCarrera(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        return "configuracion/perfil";
    }

    @PostMapping("/actualizar")
    public String actualizarInfoUsuario(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                                    @RequestParam("newNombre") String newNombre,
                                                    @RequestParam("newApellidoP") String newApellidoP,
                                                    @RequestParam("newApellidoM") String newApellidoM){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        usuario.setNombre(newNombre);
        usuario.setApellidoP(newApellidoP);
        usuario.setApellidoM(newApellidoM);
        usuarioServicio.actualizarUsuario(usuario);
        return "redirect:/configuracion/perfil?exito";
    }

    @PostMapping("/actualizarEmail")
    public String actualizarCorreo(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                                @RequestParam("newCorreo") String newCorreo,
                                                @RequestParam("password") String password){
        usuarioServicio.actualizarEmail(userDetails.getUsername(), newCorreo, password);
        
        return "redirect:/logout";
    }
  
    @PostMapping("/actualizarPassword")
    public String actualizarPassword(Model model, @AuthenticationPrincipal UserDetails userDetails,
                                                @RequestParam("newPassword") String newPswd){
        usuarioServicio.actualizarPass(userDetails.getUsername(), newPswd);
        return "redirect:/configuracion/perfil?exito";
    }
    
}
