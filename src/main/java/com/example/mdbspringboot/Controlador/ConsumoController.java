package com.example.mdbspringboot.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.Consumo;
import com.example.mdbspringboot.Modelo.ReservaHabitacion;
import com.example.mdbspringboot.Repositorio.ConsumoRepository;
import com.example.mdbspringboot.Repositorio.ReservaHabitacionRepository;
import com.example.mdbspringboot.Repositorio.ServicioRepository;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;


@Controller
public class ConsumoController {
    
    @Autowired
    ConsumoRepository consumoRepository;

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ReservaHabitacionRepository reservaHabitacionRepository;


    @GetMapping("/RF6")
    String mostrar(Model model){
        List<Consumo> consumos = consumoRepository.findAll();
        for(Consumo consumo: consumos){
            consumo.setServicio(servicioRepository.findById(consumo.getServicio()).get().getNombre());
        }
        model.addAttribute("datos", consumos);

        return "/RF6.html";
    }

    @GetMapping("/RF6/new")
    String nuevo(Model model){
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("servicios", servicioRepository.findAll());
        return "Formularios/RF6A.html";
    }

    @GetMapping("/RF6/new/reservas")
    String nuevo2(Model model, @RequestParam("sumaTotal")  String sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  String numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario, @RequestParam("descripcion") String descripcion){

        List<ReservaHabitacion> reservas = reservaHabitacionRepository.findUsuariosId(idUsuario);
        for(int i = 0; i < reservas.size();i++){
            ReservaHabitacion reserva = reservas.get(i);
            reserva.setPlanConsumo(reserva.getFechaInicio() + " " + reserva.getFechaFin());
        }

        model.addAttribute("reservas", reservas);
        model.addAttribute("sumaTotal", sumaTotal);
        model.addAttribute("fechaConsumo", fechaConsumo);
        model.addAttribute("numConsumos", numConsumos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("descripcion", descripcion);

        return "/Formularios/RF6B.html";
    }

    @PostMapping("/RF6/new/save")
    String save(@RequestParam("sumaTotal")  String sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  String numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario, @RequestParam("reservaHabitacion") String reservaHabitacion,@RequestParam("descripcion") String descripcion){

        Consumo consumo = new Consumo(null, sumaTotal, fechaConsumo, numConsumos, descripcion,servicio, usuarioRepository.findById(idUsuario).get(), reservaHabitacion);
        consumoRepository.insert(consumo);

        return "redirect:/RF6";
    }
    @GetMapping("/RF6/{id}/delete")
    String eliminar(@PathVariable("id") String id){
        consumoRepository.deleteById(id);

        return "redirect:/RF6";
    }

    @GetMapping("/RF6/{id}/edit")
    String edit(Model model, @PathVariable("id") String id){
        model.addAttribute("servicios", servicioRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("item", consumoRepository.findById(id).get());
        model.addAttribute("id", id);

        return "Edits/RF6.html";
    }

    @GetMapping("/RF6/{id}/edit/reserva")
    String reserva(Model model,@PathVariable("id") String id,@RequestParam("sumaTotal")  String sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  String numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario,@RequestParam("descripcion") String descripcion  ){

        List<ReservaHabitacion> reservas = reservaHabitacionRepository.findUsuariosId(idUsuario);
        for(int i = 0; i < reservas.size();i++){
            ReservaHabitacion reserva = reservas.get(i);
            reserva.setPlanConsumo(reserva.getFechaInicio() + " " + reserva.getFechaFin());
        }

        model.addAttribute("id", id);
        model.addAttribute("reservas", reservas);
        model.addAttribute("sumaTotal", sumaTotal);
        model.addAttribute("fechaConsumo", fechaConsumo);
        model.addAttribute("numConsumos", numConsumos);
        model.addAttribute("servicio", servicio);
        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("descripcion", descripcion);

        return "Edits/RF6A.html";
    }

    @PostMapping("/RF6/{id}/edit/save")
    String post(@PathVariable("id") String id,@RequestParam("sumaTotal")  String sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  String numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario,@RequestParam("descripcion") String descripcion, @RequestParam("reservaHabitacion") String reservaHabitacion ){

        Consumo consumo = consumoRepository.findById(id).get();
        consumo.setReservaHabitacion(reservaHabitacion);
        consumo.setDescripcion(descripcion);
        consumo.setFechaConsumo(fechaConsumo);
        consumo.setNumConsumos(numConsumos);
        consumo.setServicio(servicio);
        consumo.setSumaTotal(sumaTotal);
        consumo.setUsuario(usuarioRepository.findById(idUsuario).get());

        consumoRepository.save(consumo);

        return "redirect:/RF6";
    }
}
