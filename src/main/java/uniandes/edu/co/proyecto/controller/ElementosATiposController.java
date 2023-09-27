package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.*;
import uniandes.edu.co.proyecto.modelo.TipoHabitacion;
import uniandes.edu.co.proyecto.repository.*;


@Controller
public class ElementosATiposController {

    @Autowired
    private ElementoHabitacionRepository elementoHabitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired 
    private ElementosATiposRepository elementosATiposRepository;

    @GetMapping("/elementosatipos/new")
    public String gustanForm(Model model) {
        model.addAttribute("elementoshabitaciones", elementoHabitacionRepository.darElementosHabitaciones());
        model.addAttribute("tiposhabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        return "elementosatiposNuevo";
    }

    @PostMapping("/elementosatipos/new/save")
    public String gustanGuardar(@ModelAttribute("tiposhabitaciones_id") Long tiposhabitaciones_id,
    @ModelAttribute("elementoshab_id") Long elementoshab_id) {

        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.darTipoHabitacion(tiposhabitaciones_id);
        ElementoHabitacion elementoHab = elementoHabitacionRepository.darElementoHabitacion(elementoshab_id);

        ElementoATipoPK pk = new ElementoATipoPK(elementoHab, tipoHabitacion);
        ElementoATipo elementosATipos = new ElementoATipo();
        elementosATipos.setPk(pk);
        elementosATiposRepository.insertarElementosATipos(elementosATipos.getPk().getId_elementoHabitacion().getId(), elementosATipos.getPk().getId_TipoHabitacion().getId());
        return "redirect:/elementosatipos";
    }
    
    
}
