package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.*;
import uniandes.edu.co.proyecto.repository.*;
import uniandes.edu.co.proyecto.repository.ElementoHabitacionRepository.RespuestaInformacionElementosHabitaciones;


@Controller
public class ElementosHabitacionesController {
    
    @Autowired
    private ElementoHabitacionRepository elementoHabitacionRepository;

    @GetMapping("/elementoshabitaciones")
    public String elementosHab(Model model, String nombre) {

        Collection<RespuestaInformacionElementosHabitaciones> informacion = elementoHabitacionRepository.darInformacionElementosHabitaciones();
        model.addAttribute("totalElementos", informacion.iterator().next().getTOTAL_ELEMENTOS());

        return "elementoshabitaciones";
    }

    @GetMapping("/elementoshabitaciones/new")
    public String elementoHabForm(Model model) {
        model.addAttribute("elementoHabitacion", new ElementoHabitacion());
        return "elementoHabNuevo";
    }

    @PostMapping("/elementoshabitaciones/new/save")
    public String elementoHabGuardar(@ModelAttribute ElementoHabitacion elementoHab) {
        elementoHabitacionRepository.insertarElementoHabitacion(elementoHab.getNombre());
        return "redirect:/elementoshabitaciones";
    }

    @GetMapping("/elementoshabitaciones/{id}/edit")
    public String elementoHabEditarForm(@PathVariable("id") long id, Model model) {
        ElementoHabitacion elementoHab = elementoHabitacionRepository.darElementoHabitacion(id);
        if (elementoHab != null) {
            model.addAttribute("elementoHabitacion", elementoHab);
            return "elementoHabitacionEditar";
        } else {
            return "redirect:/elementoshabitaciones";
        }
    }

    @PostMapping("/elementoshabitaciones/{id}/edit/save")
    public String elementosHabEditarGuardar(@PathVariable("id") long id, @ModelAttribute ElementoHabitacion elementoHab) {
        elementoHabitacionRepository.actualizarElementoHabitacion(((long) id), elementoHab.getNombre());
        return "redirect:/elementoshabitaciones";
    }

    @GetMapping("/elementoshabitaciones/{id}/delete")
    public String elementoHabBorrar(@PathVariable("id") long id) {
        elementoHabitacionRepository.eliminarElementoHabitacion(id);
        return "redirect:/elementoshabitaciones";
    }
}
