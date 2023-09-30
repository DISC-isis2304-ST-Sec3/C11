package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ReservaHabitacion;
import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;

@Controller
public class ReservaHabitacionController {

    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;

    @GetMapping("/reservashabitaciones/new")
    public String reservaHabitacionForm(Model model) {
        model.addAttribute("reservahabitacion", new ReservaHabitacion());
        return "reservaHabitacionNuevo";
    }

   @PostMapping("/reservashabitaciones/new/save")
   public String reservaHabitacionGuardar(@ModelAttribute ReservaHabitacion reservaHabitacion) {
       reservaHabitacionRepository.insertarReservaHabitacion(reservaHabitacion.getNumPersonas(),reservaHabitacion.getFechaInicio(),reservaHabitacion.getFechaFin(),
                                       reservaHabitacion.getUsuario().getId(),reservaHabitacion.getHabitacion().getId());
       return "redirect:/reservashabitaciones";
   }

    @GetMapping("/reservashabitaciones/{id}/edit")
    public String reservaHabitacionEditarForm(@PathVariable("id") int id, Model model) {
        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.darReservaHabitacion(id);
        if (reservaHabitacion != null) {
            model.addAttribute("reservahabitacion", reservaHabitacion);
            return "reservahabitacionEditar";
        } else {
            return "redirect:/reservashabitaciones";
        }
    }

    @PostMapping("/reservashabitaciones/{id}/edit/save")
    public String reservaHabitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute ReservaHabitacion reservaHabitacion) {
        reservaHabitacionRepository.actualizarReservaHabitacion(((long) id),reservaHabitacion.getNumPersonas(),reservaHabitacion.getFechaInicio(),reservaHabitacion.getFechaFin(),
                                        reservaHabitacion.getUsuario().getId(),reservaHabitacion.getHabitacion().getId());
        return "redirect:/reservashabitaciones";
    }

    @GetMapping("/reservashabitaciones/{id}/delete")
    public String reservaHabitacionEliminar(@PathVariable("id") long id) {
        reservaHabitacionRepository.eliminarReservaHabitacion(id);
        return "redirect:/reservashabitaciones";
    }

    

    
    
}
