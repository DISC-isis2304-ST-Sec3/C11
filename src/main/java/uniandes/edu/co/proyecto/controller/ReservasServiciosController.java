package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ReservaServicio;
import uniandes.edu.co.proyecto.repository.ReservaServicioRepository;

@Controller
public class ReservasServiciosController {
    
    @Autowired
    private ReservaServicioRepository reservaServicioRepository;

    @GetMapping("/reservasservicios/new")
    public String reservaServicioForm(Model model) {
        model.addAttribute("reservaservicio", new ReservaServicio());
        return "reservaServicioNuevo";
    }

    @PostMapping("/reservasservicios/new/save")
    public String reservaServicioGuardar(@ModelAttribute ReservaServicio reservaServicio) {
        reservaServicioRepository.insertarReservaServicio(reservaServicio.getNumPersonas(),reservaServicio.getFechaInicio(),reservaServicio.getFechaFin(),
                                        reservaServicio.getUsuario().getId(),reservaServicio.getServicio().getId());
        return "redirect:/reservasservicios";
    }

    @GetMapping("/reservasservicios/{id}/edit")
    public String reservaServicioEditarForm(@PathVariable("id") int id, Model model) {
        ReservaServicio reservaServicio = reservaServicioRepository.darReservaServicio(id);
        if (reservaServicio != null) {
            model.addAttribute("reservaservicio", reservaServicio);
            return "reservaservicioEditar";
        } else {
            return "redirect:/reservasservicios";
        }
    }

    @PostMapping("/reservasservicios/{id}/edit/save")
    public String reservaServicioEditarGuardar(@PathVariable("id") long id, @ModelAttribute ReservaServicio reservaServicio) {
        reservaServicioRepository.actualizarReservaServicio(((long) id),reservaServicio.getNumPersonas(),reservaServicio.getFechaInicio(),reservaServicio.getFechaFin(),
                                        reservaServicio.getUsuario().getId(),reservaServicio.getServicio().getId());
        return "redirect:/reservasservicios";
    }

    @GetMapping("/reservasservicios/{id}/delete")
    public String reservaServicioEliminar(@PathVariable("id") long id) {
        reservaServicioRepository.eliminarReservaServicio(id);
        return "redirect:/reservasservicios";
    }

}
