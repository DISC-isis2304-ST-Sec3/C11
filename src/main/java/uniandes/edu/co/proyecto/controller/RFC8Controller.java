package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ConsumoRepository;

@Controller
public class RFC8Controller {
    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/RFC8")
    private String mostrar(Model model){
        model.addAttribute("datos", consumoRepository.RFC8());
        model.addAttribute("datosaux", consumoRepository.RFC8AUX());

        return "RFC/serviciosPocaDemanda.html";
    }
}
