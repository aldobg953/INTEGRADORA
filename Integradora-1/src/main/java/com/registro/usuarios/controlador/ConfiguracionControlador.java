package com.registro.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
                                                    @RequestParam("newApellidoP") String newApellidoP){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        usuario.setNombre(newNombre);
        usuario.setApellidoP(newApellidoP);
        usuarioServicio.actualizarUsuario(usuario);
        return "redirect:/configuracion/perfil?exito&lang="+usuario.getLang();
    }

    @PostMapping("/actualizarEmail")
    public String actualizarCorreo(RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails,
                                                @RequestParam("newCorreo") String newCorreo,
                                                @RequestParam("password") String password){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        String msj = usuarioServicio.actualizarEmail(userDetails.getUsername(), newCorreo, password);
        if(msj.equals("exito")){
            return "redirect:/logout";
        }else{
            redirectAttributes.addFlashAttribute("msj",msj);
            return "redirect:/configuracion/perfil?lang="+usuario.getLang();
        }
    }
  
    @PostMapping("/actualizarPassword")
    public String actualizarPassword(RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails,
                                                @RequestParam("newPass") String newPass,
                                                @RequestParam("oldPass") String oldPass){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(usuarioServicio.actualizarPass(userDetails.getUsername(),oldPass, newPass)){
            return "redirect:/configuracion/perfil?exito&lang="+usuario.getLang();
        }else{
            redirectAttributes.addFlashAttribute("msj","La contraseña del usaurio es incorrecta");
            return "redirect:/configuracion/perfil?lang="+usuario.getLang();
        } 
    }

    @GetMapping
    public String configuracion(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        return "configuracion/configuracion";
    }

    @PostMapping("/cambiarDarkMode")
    public String cambiarDarkMode(@RequestParam(name = "miCheckbox", required = false) Boolean checkboxValu, 
                                    @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(checkboxValu!=null){
            usuarioServicio.actualizarDarkmode(true, userDetails.getUsername());
        }else{
            usuarioServicio.actualizarDarkmode(false, userDetails.getUsername());
        }
        return "redirect:/configuracion/perfil?lang="+usuario.getLang();
    }

    @PostMapping("/cambiaridioma")
    public String cambiarIdioma(@RequestParam("idioma") String idioma, 
                                    @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        if(!idioma.equals("Lenguage")){
            usuarioServicio.actualizarIdioma(idioma, userDetails.getUsername());
        }
        return "redirect:/configuracion/perfil?lang="+usuario.getLang();
    }

    @GetMapping("/selectphoto")
    public String seleccionarFoto(Model model, @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        return "configuracion/selecfoto";
    }

    @PostMapping("seleccionarperfil")
    public String cambiarFoto(@RequestParam("selectedImage") String imagen, 
    @AuthenticationPrincipal UserDetails userDetails){
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());
        System.out.println(imagen + "\n\n\n\n\n");
        return "redirect:/configuracion/perfil?lang="+usuario.getLang();
    }
    
}
