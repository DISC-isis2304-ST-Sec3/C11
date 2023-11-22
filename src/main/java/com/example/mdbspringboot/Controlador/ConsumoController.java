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
import com.example.mdbspringboot.Modelo.Habitacion;
import com.example.mdbspringboot.Modelo.ReservaHabitacion;
import com.example.mdbspringboot.Modelo.Servicio;
import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.ConsumoRepository;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
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

    @Autowired
    HabitacionRepository habitacionRepository;


    @GetMapping("/RF6")
    String mostrar(Model model){
        List<Consumo> consumos = consumoRepository.findAll();
        for(Consumo consumo: consumos){
            consumo.setServicio(servicioRepository.findById(consumo.getServicio()).get().getNombre());
            consumo.setUsuario(usuarioRepository.findById(consumo.getUsuario()).get().getNombre());
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
    String save(@RequestParam("sumaTotal")  int sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  int numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario, @RequestParam("reservaHabitacion") String reservaHabitacion,@RequestParam("descripcion") String descripcion){

        Consumo consumo = consumoRepository.insert(new Consumo(null, sumaTotal, fechaConsumo, numConsumos, descripcion,servicio, reservaHabitacion, idUsuario));
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(consumo.getReservaHabitacion());
        Servicio servicio_ = servicioRepository.findById(consumo.getServicio()).get();
        usuario.getConsumos().add(consumo);
        habitacion.getConsumos().add(consumo);

        habitacionRepository.save(habitacion);
        usuarioRepository.save(usuario);

        usuario.setFechaConsumo(fechaConsumo);
        servicio_.getUsuarios().add(usuario);

        servicioRepository.save(servicio_);
        

        return "redirect:/RF6";
    }
    @GetMapping("/RF6/{id}/delete")
    String eliminar(@PathVariable("id") String id){
        Consumo consumo = consumoRepository.findById(id).get();
        Usuario usuario = usuarioRepository.findConsumosId(id);
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(consumo.getReservaHabitacion());
        Servicio servicio = servicioRepository.findById(consumo.getServicio()).get();
        for(int i = 0; i < usuario.getConsumos().size();i++){
            if(usuario.getConsumos().get(i).getId().equals(id)){
                usuario.getConsumos().remove(i);
                break;
            }
        }
        for(int i = 0; i < habitacion.getConsumos().size();i++){
            if(habitacion.getConsumos().get(i).getId().equals(id)){
                habitacion.getConsumos().remove(i);
                break;
            }
        }
        for(int i = 0; i < servicio.getUsuarios().size();i++){
            if(servicio.getUsuarios().get(i).getId().equals(usuario.getId())){
                servicio.getUsuarios().remove(i);
                break;
            }
        }
        habitacionRepository.save(habitacion);
        consumoRepository.deleteById(id);
        usuarioRepository.save(usuario);
        servicioRepository.save(servicio);


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
    String post(@PathVariable("id") String id,@RequestParam("sumaTotal")  int sumaTotal,
    @RequestParam("fechaConsumo")  String fechaConsumo,@RequestParam("numConsumos")  int numConsumos,
    @RequestParam("servicio")  String servicio, @RequestParam("idUsuario") String idUsuario,@RequestParam("descripcion") String descripcion, @RequestParam("reservaHabitacion") String reservaHabitacion ){
        Consumo consumo = consumoRepository.findById(id).get();
        String reservaHab = consumo.getReservaHabitacion();
        String servicioOrg = consumo.getServicio();
        consumo.setReservaHabitacion(reservaHabitacion);
        consumo.setDescripcion(descripcion);
        consumo.setFechaConsumo(fechaConsumo);
        consumo.setNumConsumos(numConsumos);
        consumo.setServicio(servicio);
        consumo.setSumaTotal(sumaTotal);
        consumo.setUsuario(idUsuario);

        Usuario usuario_ = usuarioRepository.findConsumosId(id);
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(consumo.getReservaHabitacion());

        Usuario usuarioService = usuario_;

        if(usuario_.getId().equals(idUsuario)){
            for(int i = 0; i < usuario_.getConsumos().size();i++){
                if(usuario_.getConsumos().get(i).getId().equals(id)){
                    usuario_.getConsumos().set(i, consumo);
                    break;
                }
            }
        }
        else{
            
            for(int i = 0; i < usuario_.getConsumos().size();i++){
                if(usuario_.getConsumos().get(i).getId().equals(id)){
                    usuario_.getConsumos().remove(i);
                    break;
                }
            }
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            usuario.getConsumos().add(consumo);
            usuarioRepository.save(usuario);

            usuarioService = usuario;

        }
        usuarioRepository.save(usuario_);
        usuarioService.setFechaConsumo(fechaConsumo);
        if(reservaHab.equals(consumo.getReservaHabitacion())){
            for(int i = 0; i < habitacion.getConsumos().size();i++){
                if(habitacion.getConsumos().get(i).getId().equals(id)){
                    habitacion.getConsumos().set(i,consumo);
                }
            }
        }
        else{
            Habitacion nuevaHab = habitacionRepository.findByReservasHabitacionesId(consumo.getReservaHabitacion());
            for(int i = 0; i < habitacion.getConsumos().size();i++){
                if(habitacion.getConsumos().get(i).getId().equals(id)){
                    habitacion.getConsumos().remove(i);
                }
            }
            nuevaHab.getConsumos().add(consumo);
            habitacionRepository.save(nuevaHab);
        }
        Servicio servicio_ = servicioRepository.findById(servicioOrg).get();
        if(!servicioOrg.equals(servicio)){
            for(int i = 0; i < servicio_.getUsuarios().size();i++){
                if(servicio_.getUsuarios().get(i).getId().equals(usuario_.getId())){
                    servicio_.getUsuarios().remove(i);
                    break;
                }
            }
            Servicio newServicio = servicioRepository.findById(servicio).get();
            newServicio.getUsuarios().add(usuarioService);
            servicioRepository.save(newServicio);
        }
        else{
            for(int i = 0; i < servicio_.getUsuarios().size();i++){
                if(servicio_.getUsuarios().get(i).getId().equals(usuario_.getId())){
                    servicio_.getUsuarios().set(i, usuarioService);
                }
            }
        }
        servicioRepository.save(servicio_);
        consumoRepository.save(consumo);
        habitacionRepository.save(habitacion);

        return "redirect:/RF6";
    }
}
