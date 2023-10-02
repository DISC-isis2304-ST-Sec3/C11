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

    @GetMapping("/consumos")
    public String consumoList(Model model) {
        model.addAttribute("consumos", consumoRepository.darConsumos());
        return "consumos";
    }

    @GetMapping("/consumos/new")
    public String consumoForm(Model model) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("reservashabitaciones", reservaHabitacionRepository.darReservasHabitaciones());
        return "/fragments/consumosForm";
    }

    @PostMapping("/consumos/new/save")
    public String consumoGuardar(@ModelAttribute Consumo consumo) {
        consumoRepository.insertarConsumo(consumo.getSumaTotal(), consumo.getNumConsumos(), consumo.getNombre(), consumo.getReservaHabitacion().getId(), consumo.getServicio().getId());
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/edit")
    public String consumoEditarForm(@PathVariable("id") int id, Model model) {
        Consumo consumo = consumoRepository.darConsumo(id);
        if (consumo != null) {
            model.addAttribute("consumo", consumo);
            model.addAttribute("servicios", servicioRepository.darServicios());
            model.addAttribute("reservashabitaciones", reservaHabitacionRepository.darReservasHabitaciones());
            return "/fragments/editConsumos";
        } else {
            return "redirect:/consumos";
        }
    }

    @PostMapping("/consumos/{id}/edit/save")
    public String consumoEditar(@PathVariable("id") int id, @ModelAttribute Consumo consumo) {
        consumoRepository.actualizarConsumo(id, consumo.getSumaTotal(), consumo.getNumConsumos(), consumo.getNombre(), consumo.getReservaHabitacion().getId(), consumo.getServicio().getId());
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/delete")
    public String consumoBorrar(@PathVariable("id") int id) {
        consumoRepository.eliminarConsumo(id);
        return "redirect:/consumos";
    }

   

    
}