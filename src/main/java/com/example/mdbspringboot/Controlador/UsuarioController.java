package com.example.mdbspringboot.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/login")
    private String hola(Model model, @RequestParam("nombreusuario") String nombreusuario, @RequestParam("contrasena") String constrasena){
        Usuario usuario = usuarioRepository.findCredentials(nombreusuario,constrasena);
        if(usuario == null){
             return "/index.html";
        }
        else{
            model.addAttribute("usuario", usuario);
            model.addAttribute("permisos", usuario.getTipoUsuario().getPermisos());
            return "/menuOpciones.html";
        }
    }
}
