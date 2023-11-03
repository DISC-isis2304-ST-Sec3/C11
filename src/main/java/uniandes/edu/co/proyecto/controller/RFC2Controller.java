package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ConsumoRepository;

@Controller
public class RFC2Controller {
    
    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/RFC2")
    public String formFechas(Model model){
        return "/RFCAUX/RFC2Form.html";
    }

    @GetMapping("/RFC2/mostrar")
    public String mostrar(@RequestParam(name = "fecha1") String fecha1,
                            @RequestParam(name = "fecha2") String fecha2,
                            Model model){
        model.addAttribute("datos", consumoRepository.RFC2(fecha1, fecha2));
        return "/RFC/20ServiciosPopulares.html";
    }
}
