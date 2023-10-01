package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repository.ProductoRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;

@Controller
public class ServiciosController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("productos", productoRepository.darProductos());
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "servicios";
    }
    @GetMapping("/servicios/new")
    public String servicioForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "/fragments/serviciosForm";
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio) {
        servicioRepository.insertarServicio(servicio.getNombre(),servicio.getDescripcion(), servicio.getCostoPorUnidad(),
                            servicio.getUnidad(),servicio.getHorario(),servicio.getTipoServicio(),servicio.getCapacidad());
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String servicioEditarForm(@PathVariable("id") int id, Model model) {
        Servicio servicio = servicioRepository.darServicio(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "/fragments/editservicios";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String servicioEditarGuardar(@PathVariable("id") long id, @ModelAttribute Servicio servicio) {
        servicioRepository.actualizarServicio(((long) id),servicio.getNombre(),servicio.getDescripcion(), servicio.getCostoPorUnidad()
                            ,servicio.getUnidad(),servicio.getHorario(),servicio.getTipoServicio(),servicio.getCapacidad() );
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String servicioEliminar(@PathVariable("id") long id) {
        servicioRepository.eliminarServicio(id);
        return "redirect:/servicios";
    }


}
