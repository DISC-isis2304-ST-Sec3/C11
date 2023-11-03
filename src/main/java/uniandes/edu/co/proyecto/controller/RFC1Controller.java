package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ConsumoRepository;

@Controller
public class RFC1Controller {
    

    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/RFC1")
    public String listar(Model model){
        model.addAttribute("datos", consumoRepository.RFC1());
        return "RFC/mostrarDineroRecolectado.html";
    }
}
