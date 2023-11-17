package com.example.mdbspringboot.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    private String hola(Model model){
        List<Usuario> a = usuarioRepository.findAll();
        model.addAttribute("hola", a);
        int b = 0;


        return "/index.html";
    }
}
