package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Consumo;
import uniandes.edu.co.proyecto.repository.ConsumoRepository;
import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;

@Controller
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;

    @GetMapping("/consumos/{id}")
    public String consumoList(Model model, @PathVariable("id") long id) {
        model.addAttribute("id", id);
        model.addAttribute("consumos", consumoRepository.darConsumos());
        return "consumos";
    }

    @GetMapping("/consumos/new/{id}")
    public String consumoForm(Model model,@PathVariable("id") long id) {
        model.addAttribute("id", id);
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("reservashabitaciones", reservaHabitacionRepository.darReservasHabitaciones());
        return "/fragments/consumosForm";
    }

    @PostMapping("/consumos/new/save/{id}")
    public String consumoGuardar(@ModelAttribute Consumo consumo,@PathVariable("id") long id) {
        consumoRepository.insertarConsumo(consumo.getSumaTotal(), consumo.getNumConsumos(), consumo.getNombre(), consumo.getReservaHabitacion().getId(), consumo.getServicio().getId(), consumo.getFechaconsumo(), id);
        return "redirect:/consumos/" + id;
    }

    @GetMapping("/consumos/{id}/edit/{id_user}")
    public String consumoEditarForm(@PathVariable("id") int id, Model model,@PathVariable("id_user") int id_user) {
        Consumo consumo = consumoRepository.darConsumo(id);
        if (consumo != null) {
            model.addAttribute("id",id_user);
            model.addAttribute("consumo", consumo);
            model.addAttribute("servicios", servicioRepository.darServicios());
            model.addAttribute("reservashabitaciones", reservaHabitacionRepository.darReservasHabitaciones());
            return "/fragments/editConsumos";
        } else {
             return "redirect:/consumos/" + id_user;
        }
    }

    @PostMapping("/consumos/{id}/edit/save/{id_user}")
    public String consumoEditar(@PathVariable("id") int id, @ModelAttribute Consumo consumo,@PathVariable("id_user") int id_user) {
        consumoRepository.actualizarConsumo(id, consumo.getSumaTotal(), consumo.getNumConsumos(), consumo.getNombre(), consumo.getReservaHabitacion().getId(), consumo.getServicio().getId());
        return "redirect:/consumos/" + id_user;
    }

    @GetMapping("/consumos/{id}/delete/{id_user}")
    public String consumoBorrar(@PathVariable("id") int id,@PathVariable("id_user") int id_user) {
        consumoRepository.eliminarConsumo(id);
        return "redirect:/consumos/" + id_user;
    }

   

    
}
