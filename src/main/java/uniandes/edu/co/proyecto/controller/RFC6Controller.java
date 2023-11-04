package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;

@Controller
public class RFC6Controller {
    
    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;

    @GetMapping("/RFC6")
    private String mostrar(Model model){
        model.addAttribute("datos1", reservaHabitacionRepository.RFC6A());
        model.addAttribute("datos2", reservaHabitacionRepository.RFC6B());
        model.addAttribute("datos3", reservaHabitacionRepository.RFC6C());

        return "RFC/operacionHotelAndes.html";
    }
}
