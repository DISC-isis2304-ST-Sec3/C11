package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.repository.HabitacionRepository;

@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.insertarHabitacion(habitacion.getCapacidad(), habitacion.getCostoAlejamiento(), habitacion.getNumero(), habitacion.getTipoHabitacion().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/edit")
    public String habitacionEditarForm(@PathVariable("id") int id, Model model) {
        Habitacion habitacion = habitacionRepository.darHabitacion(id);
        if (habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            return "habitacionEditar";
        } else {
            return "redirect:/habitaciones";
        }
    }

    @PostMapping("/habitaciones/{id}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute Habitacion habitacion) {
        habitacionRepository.actualizarHabitacion(((long) id), habitacion.getCapacidad(), habitacion.getCostoAlejamiento(), habitacion.getNumero(), habitacion.getTipoHabitacion().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/delete")
    public String habitacionEliminar(@PathVariable("id") long id) {
        habitacionRepository.eliminarHabitacion(id);
        return "redirect:/habitaciones";
    }

    
    
}
