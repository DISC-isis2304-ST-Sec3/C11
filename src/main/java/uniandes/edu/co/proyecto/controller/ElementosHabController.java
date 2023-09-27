package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ElementoHab;
import uniandes.edu.co.proyecto.repository.ElementoHabRepository;
import uniandes.edu.co.proyecto.repository.ElementoHabRepository.RespuestaInformacionElementosHab;

@Controller
public class ElementosHabController {
    
    @Autowired
    private ElementoHabRepository elementoHabRepository;

    @GetMapping("/elementoshab")
    public String elementosHab(Model model, String nombre) {

        Collection<RespuestaInformacionElementosHab> informacion = elementoHabRepository.darInformacionElementosHabs();
        model.addAttribute("totalElementos", informacion.iterator().next().getTOTAL_ELEMENTOS());

        return "elementoshab";
    }

    @GetMapping("/elementoshab/new")
    public String elementoHabForm(Model model) {
        model.addAttribute("elementoHab", new ElementoHab());
        return "elementoHabNuevo";
    }

    @PostMapping("/elementoshab/new/save")
    public String elementoHabGuardar(@ModelAttribute ElementoHab elementoHab) {
        elementoHabRepository.insertarElementoHab(elementoHab.getNombre());
        return "redirect:/elementoshab";
    }

    @GetMapping("/elementoshab/{id}/edit")
    public String elementoHabEditarForm(@PathVariable("id") long id, Model model) {
        ElementoHab elementoHab = elementoHabRepository.darElementoHab(id);
        if (elementoHab != null) {
            model.addAttribute("elementoHab", elementoHab);
            return "elementoHabEditar";
        } else {
            return "redirect:/elementoshab";
        }
    }

    @PostMapping("/elementoshab/{id}/edit/save")
    public String elementosHabEditarGuardar(@PathVariable("id") long id, @ModelAttribute ElementoHab elementoHab) {
        elementoHabRepository.actualizarElementoHab(((long) id), elementoHab.getNombre());
        return "redirect:/elementoshab";
    }

    @GetMapping("/elementoshab/{id}/delete")
    public String elementoHabBorrar(@PathVariable("id") long id) {
        elementoHabRepository.eliminarElementoHab(id);
        return "redirect:/elementoshab";
    }
}
