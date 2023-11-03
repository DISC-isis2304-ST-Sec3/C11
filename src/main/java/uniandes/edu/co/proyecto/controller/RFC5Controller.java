package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class RFC5Controller {
    

    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/RFC5")
    private String form(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "RFCAUX/RFC5Form";
    }

    @GetMapping("/RFC5/mostrar")
    private String mostrar(Model model, @RequestParam(name = "fecha1") String fecha1,
                            @RequestParam(name = "fecha2") String fecha2, 
                            @RequestParam(name = "usuario_id") Long usuarios_id)
    {

        model.addAttribute("datos", reservaHabitacionRepository.RFC5(usuarios_id, fecha1, fecha2));

        return "RFC/consumoDeUsuarioEnFechas";

    }
}
