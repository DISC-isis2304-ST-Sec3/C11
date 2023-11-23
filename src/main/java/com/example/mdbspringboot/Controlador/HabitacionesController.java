package com.example.mdbspringboot.Controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.mdbspringboot.Modelo.Habitacion;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
import com.example.mdbspringboot.Repositorio.TipoHabitacionRepository;


@Controller
public class HabitacionesController {
    
    @Autowired
    HabitacionRepository habitacionRepository;

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;


    @GetMapping("/RF2")
    String mostrar(Model model){
        List<Habitacion> lista = habitacionRepository.findAll();
        for(Habitacion habitacion: lista){
            String idHab = habitacion.getTipoHabitacion();
            habitacion.setTipoHabitacion(tipoHabitacionRepository.findById(idHab).get().getNombre());
        }
        model.addAttribute("datos", lista);

        return "/RF2.html";
    }

    @GetMapping("/RF2/new")
    String nuevo(Model model){
        model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.findAll());

        return "/Formularios/RF2";
    }
    @PostMapping("/RF2/new/save")
    String save(Model model, @RequestParam("numero") int numero,
                @RequestParam("costoAlojamiento") int costoAlojamiento,
                @RequestParam("tipoHabitacion") String tipoHabitacion){
        
        Habitacion habitacion = new Habitacion(null, numero, costoAlojamiento, tipoHabitacion, new ArrayList<>(),new ArrayList<>());
        
        habitacionRepository.insert(habitacion);
    
    return "redirect:/RF2";
                }
    
    @GetMapping("/RF2/{id}/delete")
    String borrar(Model model, @PathVariable("id") String id){

        if(!habitacionRepository.findById(id).get().getReservasHabitaciones().isEmpty()){
            model.addAttribute("causa", "EXISTEN RESERVAS ASOCIADAS A ESTA HABITACION");
            return "error.html";
        }

        habitacionRepository.deleteById(id);

        return "redirect:/RF2";
    }

    @GetMapping("/RF2/{id}/edit")
    String editar(Model model, @PathVariable("id") String id){
        Habitacion habitacion = habitacionRepository.findById(id).get();
        model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.findAll());
        model.addAttribute("numeroorg", habitacion.getNumero());
        model.addAttribute("costoAlojamientoorg", habitacion.getCostoAlojamiento());
        model.addAttribute("tipoorg", habitacion.getTipoHabitacion());

        return "Edits/RF2.html";
    }

    @PostMapping("/RF2/{id}/edit/save")
    String post(@PathVariable("id") String id, @RequestParam("numero") int numero,
            @RequestParam("costoAlojamiento") int costoAlojamiento, @RequestParam("tipoHabitacion") String tipoHabitacion) {
        
        Habitacion habitacion = habitacionRepository.findById(id).get();
        habitacion.setCostoAlojamiento(costoAlojamiento);
        habitacion.setNumero(numero);
        habitacion.setTipoHabitacion(tipoHabitacion);
        habitacionRepository.save(habitacion);

        return "redirect:/RF2";
    }

    @GetMapping("/RFC1")
    String RFC1(Model model) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("datos", habitacionRepository.RFC1(sdf.parse("2023-01-01"), sdf.parse("2023-12-31")));

        return "/RFC1.html";
    }

    @GetMapping("/RFC2")
    String ocupacion(Model model) throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        model.addAttribute("datos", habitacionRepository.RFC2(sdf.parse("2023-01-01"),sdf.parse("2023-12-31")));

        return "RFC2.html";
    }
    
}
