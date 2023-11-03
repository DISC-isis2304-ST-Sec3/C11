package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;

@Controller
public class RFC3Controller {
    
    @Autowired
    ReservaHabitacionRepository reservaHabitacionRepository;

    @GetMapping("/RFC3")
    private String mostrar(Model model){
        model.addAttribute("datos", reservaHabitacionRepository.RFC3());

        return "RFC/indiceOcupacionHabitaciones.html";
    }
}
