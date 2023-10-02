package uniandes.edu.co.proyecto.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Consumo;
import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.modelo.ReservaHabitacion;
import uniandes.edu.co.proyecto.repository.ConsumoRepository;
import uniandes.edu.co.proyecto.repository.HabitacionRepository;
import uniandes.edu.co.proyecto.repository.PlanesConsumoRepository;
import uniandes.edu.co.proyecto.repository.ReservaHabitacionRepository;

@Controller
public class ReservaHabitacionController {

    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private PlanesConsumoRepository planesConsumoRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/reservashabitaciones/{id_usuario}")
    public String reservaHabitacionList(Model model, @PathVariable("id_usuario") Long userId) {
        Collection<ReservaHabitacion> reservashabitaciones = reservaHabitacionRepository.darReservasHabitacionesUsuario(userId);
        model.addAttribute("reservashabitaciones", reservashabitaciones);
        model.addAttribute("id_usuario", userId);
        
        return "reservasalojamiento";
    }
    @GetMapping("/checkout")
    public String reservaHabitacionCheckout(Model model) {
        Collection<ReservaHabitacion> reservashabitaciones = reservaHabitacionRepository.darReservasHabitaciones();
        model.addAttribute("reservashabitaciones", reservashabitaciones);
        return "checkout";
    }

    @GetMapping("/reservashabitaciones/{id_usuario}/new")
    public String reservaHabitacionForm(Model model, @PathVariable("id_usuario") Long userId) {
        model.addAttribute("reservahabitacion", new ReservaHabitacion());
        model.addAttribute("id_usuario", userId);
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        model.addAttribute("planesConsumo", planesConsumoRepository.darPlanesConsumo());

        return "/fragments/reservaAlojamientoForm";
    }

   @PostMapping("/reservashabitaciones/{id_usuario}/new/save")
   public String reservaHabitacionGuardar(@ModelAttribute ReservaHabitacion reservaHabitacion, @PathVariable("id_usuario") Integer userId) {
        Collection<ReservaHabitacion> reservas = reservaHabitacionRepository.darReservasHabitacionesHabitacion(reservaHabitacion.getHabitacion().getId(), reservaHabitacion.getFechaInicio(), reservaHabitacion.getFechaFin()); 
        if(reservas.size() == 0){
        reservaHabitacion.setPlanConsumo(planesConsumoRepository.darPlanConsumo(reservaHabitacion.getPlanConsumo().getId()));
        reservaHabitacionRepository.insertarReservaHabitacion(reservaHabitacion.getNumPersonas(),reservaHabitacion.getFechaInicio(),reservaHabitacion.getFechaFin(),
                                userId,reservaHabitacion.getHabitacion().getId(), reservaHabitacion.getPlanConsumo().getId());}
       return "redirect:/reservashabitaciones/{id_usuario}";
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

    @PostMapping("/reservashabitaciones/{id_usuario}/{id}/edit/save")
    public String reservaHabitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute ReservaHabitacion reservaHabitacion, @PathVariable("id_usuario") Integer userId) {
        reservaHabitacionRepository.actualizarReservaHabitacion(((long) id),reservaHabitacion.getNumPersonas(),reservaHabitacion.getFechaInicio(),reservaHabitacion.getFechaFin(),
                                        userId,reservaHabitacion.getHabitacion().getId());
        return "redirect:/reservashabitaciones";
    }

    @GetMapping("/reservashabitaciones/{id}/{id_usuario}/delete")
    public String reservaHabitacionEliminar(@PathVariable("id") long id, @PathVariable("id_usuario") Integer userId) {
        reservaHabitacionRepository.eliminarReservaHabitacion(id);
        return "redirect:/reservashabitaciones/{id_usuario}";
    }

    @GetMapping("/reservashabitaciones/{id}/checkout")
    public String reservaHabitacionCheckout(@PathVariable("id") long id, Model model) throws ParseException {
        ReservaHabitacion reservaHabitacion =  reservaHabitacionRepository.darReservaHabitacion(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Habitacion habitacion = reservaHabitacion.getHabitacion();
        Collection<Consumo> consumos = consumoRepository.darConsumosReservaHabitacion(reservaHabitacion.getId());
        Date dateInicio = sdf.parse(reservaHabitacion.getFechaInicio());
        Date dateFin = sdf.parse(reservaHabitacion.getFechaFin());
        
        long diferenciaMilisegundos = dateFin.getTime() - dateInicio.getTime();
        long noches = (diferenciaMilisegundos / (24 * 60 * 60 * 1000)) -1;
        int total = 0;

        for (Consumo consumo : consumos) {
            total += consumo.getSumaTotal();
        }

        total += habitacion.getCostoAlojamiento() * noches;
        total *= (1 - (reservaHabitacion.getPlanConsumo().getDescuento()/100));
        model.addAttribute("total", total);
        model.addAttribute("reservahabitacion", reservaHabitacion);
        return "totalCheckout";
    }



    

    
    
}
