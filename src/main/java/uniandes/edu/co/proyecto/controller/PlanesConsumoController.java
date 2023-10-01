package uniandes.edu.co.proyecto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.PlanesConsumo;
import uniandes.edu.co.proyecto.repository.PlanesConsumoRepository;

@Controller
public class PlanesConsumoController {

    @Autowired
    private PlanesConsumoRepository planesConsumoRepository;

    @GetMapping("/planesConsumo")
    public String planesConsumo(Model model) {
        model.addAttribute("planesdeconsumo", planesConsumoRepository.darPlanesConsumo());
        return "planesDeConsumo";
    }

    @GetMapping("/planesConsumo/new")
    public String planesConsumoForm(Model model) {
        model.addAttribute("planesConsumo", new PlanesConsumo());
        return "/fragments/planConsumoForm";
    }

    @PostMapping("/planesConsumo/new/save")
    public String planesConsumoGuardar(@ModelAttribute PlanesConsumo planesConsumo) {
        planesConsumoRepository.insertarPlanConsumo(planesConsumo.getNombre(), planesConsumo.getDescuento(),planesConsumo.getDescripcion());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/{id}/edit")
    public String planesConsumoEditarForm(@PathVariable("id") int id, Model model) {
        PlanesConsumo planesConsumo =  planesConsumoRepository.darPlanConsumo(id);
        if (planesConsumo != null) {
            model.addAttribute("planesConsumo", planesConsumo);
            return "/fragments/editPlanesConsumo";
        } else {
            return "redirect:/planesConsumo";
        }
    }

    @PostMapping("/planesConsumo/{id}/edit/save")
    public String planesConsumoEditar(@PathVariable("id") int id, @ModelAttribute PlanesConsumo planesConsumo) {
        planesConsumoRepository.actualizarPlanConsumo(id, planesConsumo.getNombre(), planesConsumo.getDescuento(), planesConsumo.getDescripcion());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/{id}/delete")
    public String planesConsumoBorrar(@PathVariable("id") int id) {
        planesConsumoRepository.eliminarPlanConsumo(id);
        return "redirect:/planesConsumo";
    }


    
}
