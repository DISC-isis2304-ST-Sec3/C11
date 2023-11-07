package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ConsumoRepository;

@Controller
public class RFC11Controller {
    
    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/RFC11")
    private String from(){
        return "RFCAUX/RFC11Form.html";
    }

    @GetMapping("/RFC11/mostrar")
    private String mostrar(Model model, @RequestParam("razon") String razon){
        model.addAttribute("datos1", consumoRepository.RFC11A(razon));
        model.addAttribute("datos2", consumoRepository.RFC11B());

        return "/RFC/RFC11.html";
    }
}
