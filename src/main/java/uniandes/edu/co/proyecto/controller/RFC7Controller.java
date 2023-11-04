package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class RFC7Controller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/RFC7")
    private String mostrar(Model model){
        model.addAttribute("datos", usuarioRepository.RFC7());

        return "RFC/buenosClientes.html";
    }
    
}
