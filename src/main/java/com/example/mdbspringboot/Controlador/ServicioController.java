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

import com.example.mdbspringboot.Modelo.Producto;
import com.example.mdbspringboot.Modelo.Servicio;
import com.example.mdbspringboot.Repositorio.ConsumoRepository;
import com.example.mdbspringboot.Repositorio.ServicioRepository;

@Controller
public class ServicioController {
    
    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    ConsumoRepository consumoRepository;

    @GetMapping("/RF3")
    String mostrar(Model model){
        model.addAttribute("datos", servicioRepository.findAll());
        return "/RF3.html";
    }

    @GetMapping("/RF3/new")
    String neww(){
        return "/Formularios/RF3.html";

    }

    @PostMapping("/RF3/new/save")
    String edit(Model model, @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion, @RequestParam("unidad") int unidad,
        @RequestParam("costoPorUnidad") int costoPorUnidad,@RequestParam("horario") String horario,
        @RequestParam("tipoServicio") String tipoServicio,@RequestParam("capacidad") int capacidad,
        @RequestParam("listaProductos") String listaProductos){

        List<Producto> lista = new ArrayList<>();
        for(String elem: listaProductos.split(",")){
            String[] split = elem.split(":");
            lista.add(new Producto(split[0], Integer.parseInt(split[1])));
        }

        Servicio servicio = new Servicio(null, nombre, descripcion, costoPorUnidad, unidad, horario, tipoServicio, capacidad, lista, new ArrayList<>());
        servicioRepository.insert(servicio);

        return "redirect:/RF3";
    }

    @GetMapping("/RF3/{id}/edit")
    String edit(Model model, @PathVariable("id") String id){
        Servicio servicio = servicioRepository.findById(id).get();
        String linea = "";
        for(int i = 0; i < servicio.getProductos().size();i++){
            if(i != servicio.getProductos().size()-1){
                linea += servicio.getProductos().get(i).getNombre() + ":" + servicio.getProductos().get(i).getCosto() + "," ;
            }
            else{
                linea += servicio.getProductos().get(i).getNombre() + ":" + servicio.getProductos().get(i).getCosto() ;
            }
        }
        model.addAttribute("servicio", servicio);
        model.addAttribute("lista", linea);
        return "/Edits/RF3.html";
    }

    @PostMapping("/RF3/{id}/edit/save")
    String editt(Model model, @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion, @RequestParam("unidad") int unidad,
        @RequestParam("costoPorUnidad") int costoPorUnidad,@RequestParam("horario") String horario,
        @RequestParam("tipoServicio") String tipoServicio,@RequestParam("capacidad") int capacidad,
        @RequestParam("listaProductos") String listaProductos,@PathVariable("id") String id){

        List<Producto> lista = new ArrayList<>();
        for(String elem: listaProductos.split(",")){
            String[] split = elem.split(":");
            lista.add(new Producto(split[0], Integer.parseInt(split[1])));
        }

        Servicio servicio = servicioRepository.findById(id).get();
        servicio.setCapacidad(capacidad);
        servicio.setCostoPorUnidad(costoPorUnidad);
        servicio.setDescripcion(descripcion);
        servicio.setHorario(horario);
        servicio.setNombre(nombre);
        servicio.setProductos(lista);
        servicio.setTipoServicio(tipoServicio);
        servicio.setUnidad(costoPorUnidad);  
        
        servicioRepository.save(servicio);

        return "redirect:/RF3";
    }

    @GetMapping("/RF3/{id}/delete")
    String delete(Model model, @PathVariable("id") String id){

        if(!consumoRepository.findByIdServicio(id).isEmpty()){
            model.addAttribute("causa", "EXISTEN CONSUMOS ASOCIADOS A ESTE SERVICIO");
            return "error.html";
        }
        servicioRepository.deleteById(id);
        return "redirect:/RF3";
    }

    @GetMapping("/RFC4")
    String RFC4(Model model){

        model.addAttribute("servicios", servicioRepository.findAll());

        return "Formularios/RFC4";
    }

    @GetMapping("/RFC4/mostrar")
    String RFC4Mostrar(Model model, @RequestParam("fecha1") String fecha1,
        @RequestParam("fecha2") String fecha2, @RequestParam("servicio") String servicio,
        @RequestParam("agrupamiento") String agrupamiento, @RequestParam("ordenamiento") String ordenamiento,
        @RequestParam("orden") int orden) throws ParseException{

            if(ordenamiento.equals("agrupamiento")){
                ordenamiento = agrupamiento;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            model.addAttribute("datos", servicioRepository.RFC4(servicio, sdf.parse(fecha1), sdf.parse(fecha2), agrupamiento, ordenamiento, orden));

        return "/RFC4.html";
    }
}
