package uniandes.edu.co.proyecto.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.*;
import uniandes.edu.co.proyecto.repository.*;

@Controller
public class TiposHabitacionesController {
    
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tiposhabitaciones")
    public String listarTiposHabitaciones(Model model) {
        model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
    return "tiposHabitaciones";
}




    @GetMapping("/tiposhabitaciones/new")
    public String tipohabitacionForm(Model model) {
    model.addAttribute("tipohabitacion", new TipoHabitacion());
    return "fragments/tiposHabitacionesForm"; 
    }


    @PostMapping("/tiposhabitaciones/new/save")
    public String tiposhabitacionesGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.insertarTipoHabitacion(tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad());
        return "redirect:/tiposhabitaciones"; 
}

    @PostMapping("/tiposhabitaciones/{id}/edit")
    public String editarTipoHabitacion(@PathVariable Long id, @RequestParam String nombre, @RequestParam int capacidad) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.darTipoHabitacion(id);
        
        if (tipoHabitacion != null) {
            // Actualiza los valores del tipo de habitación con los datos proporcionados
            tipoHabitacion.setNombre(nombre);
            tipoHabitacion.setCapacidad(capacidad);

            // Guarda los cambios en la base de datos
            tipoHabitacionRepository.actualizarTipoHabitacion(capacidad, nombre, null);

            // Redirige al usuario a la lista de tipos de habitaciones
            return "redirect:/tiposhabitaciones";
        } else {
            return "redirect:/tiposhabitaciones";
        }
    }


    @PostMapping("/tiposhabitaciones/{id}/edit/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.actualizarTipoHabitacion(id, tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad());
        return "redirect:/tiposhabitaciones"; 
    }
    @GetMapping("/tiposhabitaciones/{id}/delete")
    public String tipoHabitacionBorrar(@PathVariable("id") long id) {
        tipoHabitacionRepository.eliminarTipoHabitacion(id);
        return "redirect:/tiposhabitaciones";
    }
}
