package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ConsumoRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;


@Controller
public class RFC9Controller {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/RFC9")
    private String form(Model model){
        
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "RFCAUX/RFC9Form.html";
    }

    @GetMapping("/RFC9/mostrar")
    private String mostrar(Model model, @RequestParam(name = "fecha1") String fecha1,
                            @RequestParam(name = "fecha2") String fecha2,
                            @RequestParam(name = "ordenamiento") String ordenamiento,
                            @RequestParam(name = "agrupamiento") String agrupamiento,
                            @RequestParam(name = "servicio_id") String servicio_id){
    model.addAttribute("datos", consumoRepository.RFC9(fecha1, fecha2, agrupamiento, ordenamiento, servicio_id));

    return "RFC/RFC9.html";
                            }
}
