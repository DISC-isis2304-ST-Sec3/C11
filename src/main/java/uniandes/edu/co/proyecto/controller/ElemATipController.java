package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ElemATip;
import uniandes.edu.co.proyecto.modelo.ElemATipPK;
import uniandes.edu.co.proyecto.modelo.ElementoHab;
import uniandes.edu.co.proyecto.modelo.TipoHabitacion;
import uniandes.edu.co.proyecto.repository.ElemATipRepository;
import uniandes.edu.co.proyecto.repository.ElementoHabRepository;
import uniandes.edu.co.proyecto.repository.TipoHabitacionRepository;

@Controller
public class ElemATipController {

    @Autowired
    private ElementoHabRepository elementoHabRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired 
    private ElemATipRepository elemATipRepository;

    @GetMapping("/elematip/new")
    public String gustanForm(Model model) {
        model.addAttribute("elementoshab", elementoHabRepository.darElementosHab());
        model.addAttribute("tiposhabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        return "elematipNuevo";
    }

    @PostMapping("/elematip/new/save")
    public String gustanGuardar(@ModelAttribute("tiposhabitaciones_id") Long tiposhabitaciones_id,
    @ModelAttribute("elementoshab_id") Long elementoshab_id) {

        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.darTipoHabitacion(tiposhabitaciones_id);
        ElementoHab elementoHab = elementoHabRepository.darElementoHab(elementoshab_id);

        ElemATipPK pk = new ElemATipPK(elementoHab, tipoHabitacion);
        ElemATip elemATip = new ElemATip();
        elemATip.setPk(pk);
        elemATipRepository.insertarElemATip(elemATip.getPk().getId_elementoHab().getId(), elemATip.getPk().getId_TipoHabitacion().getId());
        return "redirect:/elematip";
    }
    
    
}
