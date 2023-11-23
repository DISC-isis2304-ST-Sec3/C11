package com.example.mdbspringboot.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.ElementoHabitacion;
import com.example.mdbspringboot.Modelo.TipoHabitacion;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
import com.example.mdbspringboot.Repositorio.TipoHabitacionRepository;

@Controller
public class TiposHabitacionesController {

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    HabitacionRepository habitacionRepository;


    @GetMapping("/RF1")
    String mostrar(Model model){
        List<TipoHabitacion> datos =  tipoHabitacionRepository.findAll();
        model.addAttribute("datos",datos);
        return "/RF1.html";
    }

    @GetMapping("/RF1/new")
    String nuevo(){
        return "Formularios/RF1.html";
    }

    @PostMapping("/RF1/new/save")
    String guardar(Model model, @RequestParam("capacidad") String capacidad,
    @RequestParam("nombre") String nombre, @RequestParam("listaElementos") String listaElementos){
        List<ElementoHabitacion> lista = new ArrayList<>();
        for(String elemento: listaElementos.split(",")){
            lista.add(new ElementoHabitacion(elemento));
        }
        TipoHabitacion tipoHabitacion = new TipoHabitacion(null, lista , nombre, Integer.parseInt(capacidad) );

        tipoHabitacionRepository.insert(tipoHabitacion);

        return "redirect:/RF1";
    }

    @GetMapping("/RF1/{id}/delete")
    String borrar(Model model, @PathVariable("id") String id){

        if(!habitacionRepository.findByTipoHabitacion(id).isEmpty()){
            model.addAttribute("causa", "HAY HABITACIONES CON ESTE TIPO DE HABITACION ASOCIADO");
            return "error.html";
        }

        tipoHabitacionRepository.deleteById(id);
        return "redirect:/RF1";
    }

    @GetMapping("/RF1/{id}/edit")
    String editar(Model model, @PathVariable("id") String id){

        Optional<TipoHabitacion> optional = tipoHabitacionRepository.findById(id);
        TipoHabitacion tipoHabitacion = optional.get();
        String elementosorg = "";
        for(int i = 0; i < tipoHabitacion.getElementosHabitaciones().size();i++){
            if(i != tipoHabitacion.getElementosHabitaciones().size()-1){
            elementosorg += tipoHabitacion.getElementosHabitaciones().get(i).getNombre() + ",";}
            else{
                elementosorg += tipoHabitacion.getElementosHabitaciones().get(i).getNombre();
            }
        }
        model.addAttribute("id", id);
        model.addAttribute("nombreorg" ,  tipoHabitacion.getNombre());
        model.addAttribute("capacidadorg", tipoHabitacion.getCapacidad());
        model.addAttribute("elementosorg", elementosorg);

        return "Edits/RF1.html";
    }
    @PostMapping("/RF1/{id}/edit/save")
    String saveEdit(Model model, @PathVariable("id") String id,  @RequestParam("capacidad") String capacidad,
    @RequestParam("nombre") String nombre, @RequestParam("listaElementos") String listaElementos){
        List<ElementoHabitacion> lista = new ArrayList<>();
        for(String elem: listaElementos.split(",")){
            lista.add(new ElementoHabitacion(elem));
        }

        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id).get();
        tipoHabitacion.setCapacidad(Integer.parseInt(capacidad));
        tipoHabitacion.setElementosHabitaciones(lista);
        tipoHabitacion.setNombre(nombre);

        tipoHabitacionRepository.save(tipoHabitacion);

        return "redirect:/RF1";
    }
}
