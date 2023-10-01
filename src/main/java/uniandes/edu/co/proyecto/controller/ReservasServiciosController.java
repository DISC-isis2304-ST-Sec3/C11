package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ReservaHabitacion;
import uniandes.edu.co.proyecto.modelo.ReservaServicio;
import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repository.ReservaServicioRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;

@Controller
public class ReservasServiciosController {
    
    @Autowired
    private ReservaServicioRepository reservaServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    

    @GetMapping("/reservasservicios/{id_usuario}")
    public String listarReservasServicios(@PathVariable("id_usuario") int id_usuario, Model model) {
        model.addAttribute("reservasservicios", reservaServicioRepository.darReservaPorUsuario(id_usuario));
        model.addAttribute("id_usuario", id_usuario);
        return "reservaservicio";
    }

    @GetMapping("/reservasservicios/{id_usuario}/new")
    public String reservaServicioForm(Model model, @PathVariable("id_usuario") int id_usuario) {
        model.addAttribute("reservaservicio", new ReservaHabitacion());
        model.addAttribute("id_usuario", id_usuario);
        model.addAttribute("servicios", servicioRepository.darServicios());


        return "/fragments/reservaServicioForm";
    }

    @PostMapping("/reservasservicios/{id_usuario}/new/save")
    public String reservaServicioGuardar(@ModelAttribute ReservaServicio reservaServicio, @PathVariable("id_usuario") int id_usuario) {
        Servicio servicio = servicioRepository.darServicio(reservaServicio.getServicio().getId());
        Collection<ReservaServicio> reservas = reservaServicioRepository.darReservasServiciosPorId(servicio.getId(), reservaServicio.getFechaInicio(), reservaServicio.getFechaFin());
        if(reservas.size() < servicio.getCapacidad()){
        reservaServicio.setServicio(servicio);
        reservaServicioRepository.insertarReservaServicio(reservaServicio.getNumPersonas(),reservaServicio.getFechaInicio(),reservaServicio.getFechaFin(),
                                        id_usuario,reservaServicio.getServicio().getId());}
        return "redirect:/reservasservicios/{id_usuario}";
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

    @GetMapping("/reservasservicios/{id}/{id_usuario}/delete")
    public String reservaServicioEliminar(@PathVariable("id") long id, @PathVariable("id_usuario") int id_usuario) {
        reservaServicioRepository.eliminarReservaServicio(id);
        return "redirect:/reservasservicios/{id_usuario}";
    }



}
