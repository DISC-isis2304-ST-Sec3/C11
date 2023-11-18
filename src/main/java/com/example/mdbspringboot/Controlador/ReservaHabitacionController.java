package com.example.mdbspringboot.Controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.Habitacion;
import com.example.mdbspringboot.Modelo.ReservaHabitacion;
import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
import com.example.mdbspringboot.Repositorio.PlanConsumoRepository;
import com.example.mdbspringboot.Repositorio.ReservaHabitacionRepository;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;

@Controller
public class ReservaHabitacionController {
    
    @Autowired
    ReservaHabitacionRepository reservaHabitacionRepository;

    @Autowired
    PlanConsumoRepository planConsumoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    HabitacionRepository habitacionRepository;

    @GetMapping("/RF4")
    String mostrar(Model model, @RequestParam("id") String id){
        model.addAttribute("id", id);
        List<ReservaHabitacion> reservas = reservaHabitacionRepository.findUsuariosId(id);
        for(ReservaHabitacion res: reservas){
            Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(res.getId());
            res.setPlanConsumo(Integer.toString(habitacion.getNumero()));
        }
        model.addAttribute("datos", reservas);
        return "/RF4.html";
    }

    @GetMapping("/RF4/new")
    String nuevo(Model model, @RequestParam("id") String id){
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("planesConsumo", planConsumoRepository.findAll());
        model.addAttribute("id", id);
        return "Formularios/RF4.html";
    }

    @PostMapping("RF4/new/save")
    String guardar(Model model, @RequestParam("fechaInicio") String fechaInicio,@RequestParam("fechaFin") String fechaFin,
    @RequestParam("numPersonas") int numPersonas ,@RequestParam("planConsumo") String  planConsumo,@RequestParam("habitacion") String habitacion,
    @RequestParam("id") String id) throws ParseException{
        Habitacion habitacion_ = habitacionRepository.findById(habitacion).get();
        boolean sePuede = true;
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date inicio = formato.parse(fechaInicio);
        Date fin = formato.parse(fechaFin);
        for(ReservaHabitacion res: habitacion_.getReservasHabitaciones()){
            Date inicio1 = formato.parse(res.getFechaInicio());
            Date fin1 = formato.parse(res.getFechaFin());

            if(!inicio.after(fin1) && !fin.before(inicio1)){
                sePuede = false;
                break;
            }
        }

        if(sePuede){
            if(planConsumo.equals("--")){
            planConsumo = null;
            }
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuarioRepository.findById(id).get());
            ReservaHabitacion reservaHabitacion = new ReservaHabitacion(null, planConsumo, usuarios, null, null, numPersonas, fechaInicio, fechaFin);
            
            habitacion_.getReservasHabitaciones().add(reservaHabitacion);
            model.addAttribute("id", id);
            reservaHabitacionRepository.insert(reservaHabitacion);
            habitacionRepository.save(habitacion_);
        }
        return "redirect:/RF4?id=" + id;
    }

    @GetMapping("RF4/{id}/{numHab}/edit")
    String editar(Model model, @PathVariable("id") String id, @PathVariable("numHab") int numHab, @RequestParam("idUsuario") String idUsuario){
        model.addAttribute("planesConsumo", planConsumoRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("idHab", habitacionRepository.findByNumero(numHab).getId());
        model.addAttribute("id", id);
        model.addAttribute("reserva", reservaHabitacionRepository.findById(id).get());
        model.addAttribute("idUsuario", idUsuario);
        return "Edits/RF4.html";
    }

    @PostMapping("RF4/{id}/{idHab}/edit/save")
    String editarSave(Model model, @PathVariable("id") String id,@PathVariable("idHab") String idHab,
    @RequestParam("fechaInicio") String fechaInicio,@RequestParam("fechaFin") String fechaFin,
    @RequestParam("numPersonas") int numPersonas ,@RequestParam("planConsumo") String  planConsumo,@RequestParam("habitacion") String habitacion,
    @RequestParam("idUsuario") String idUsuario){

        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        reservaHabitacion.setFechaFin(fechaFin);
        reservaHabitacion.setFechaInicio(fechaInicio);
        reservaHabitacion.setNumPersonas(numPersonas);
        reservaHabitacion.setPlanConsumo(planConsumo);
        
        Habitacion habitacion_ = habitacionRepository.findById(idHab).get();

        if(idHab.equals(habitacion)){
            List<ReservaHabitacion> reservas = habitacion_.getReservasHabitaciones();
            for(int i = 0; i< reservas.size();i++){
                if(reservas.get(i).getId().equals(id)){
                    reservas.set(i, reservaHabitacion);
                    habitacion_.setReservasHabitaciones(reservas);
                    break;
                }
            }
        }
        else{
            List<ReservaHabitacion> reservas = habitacion_.getReservasHabitaciones();
            for(int i = 0; i< reservas.size();i++){
                if(reservas.get(i).getId().equals(id)){
                    reservas.remove(i);
                    habitacion_.setReservasHabitaciones(reservas);
                    break;
                }
            }
            Habitacion nuevaHabitacion = habitacionRepository.findById(habitacion).get();
            nuevaHabitacion.getReservasHabitaciones().add(reservaHabitacion);
            habitacionRepository.save(nuevaHabitacion);
        }
        

        habitacionRepository.save(habitacion_);
        reservaHabitacionRepository.save(reservaHabitacion);

        return "redirect:/RF4?id=" + idUsuario;
    }

    @GetMapping("RF4/{id}/{idHab}/delete")
    String delete(@PathVariable("id") String id, @PathVariable("idHab") int idHab, @RequestParam("idUsuario") String idUsuario){
        Habitacion habitacion = habitacionRepository.findByNumero(idHab);
        List<ReservaHabitacion> reservaHabitaciones = habitacion.getReservasHabitaciones();
        for(int i = 0; i< reservaHabitaciones.size(); i++){
            if(reservaHabitaciones.get(i).getId().equals(id)){
                reservaHabitaciones.remove(i);
                habitacion.setReservasHabitaciones(reservaHabitaciones);
                break;
            }
        }

        habitacionRepository.save(habitacion);
        reservaHabitacionRepository.deleteById(id);

        return "redirect:/RF4?id=" + idUsuario;
    }
}
