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

import com.example.mdbspringboot.Modelo.Consumo;
import com.example.mdbspringboot.Modelo.Habitacion;
import com.example.mdbspringboot.Modelo.ReservaHabitacion;
import com.example.mdbspringboot.Modelo.TipoUsuario;
import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.ConsumoRepository;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
import com.example.mdbspringboot.Repositorio.PlanConsumoRepository;
import com.example.mdbspringboot.Repositorio.ReservaHabitacionRepository;
import com.example.mdbspringboot.Repositorio.TipoHabitacionRepository;
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

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    ConsumoRepository consumoRepository;


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
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        
        Date inicio = formato.parse(fechaInicio);
        Date fin = formato.parse(fechaFin);

        for(ReservaHabitacion res: habitacion_.getReservasHabitaciones()){
            Date inicio1 = formato.parse(res.getFechaInicio());
            Date fin1 = formato.parse(res.getFechaFin());

            if(!inicio.after(fin1) && !fin.before(inicio1)){
                model.addAttribute("causa", "HABITACION RESERVADA EN ESE LAPSO");
                return "error.html";
            }
        }
        if(numPersonas > tipoHabitacionRepository.findById(habitacionRepository.findById(habitacion).get().getTipoHabitacion()).get().getCapacidad() || numPersonas <= 0){
            model.addAttribute("causa", "CAPACIDAD DE LA HABITACION EXCEDIDA Y/O INVALIDA");
            return "error.html";
        }

        if(inicio.after(fin) || fin.before(inicio)){
            model.addAttribute("causa", "FECHAS NO VALIDAS");
            return "error.html";
        }

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
    String delete(Model model, @PathVariable("id") String id, @PathVariable("idHab") int idHab, @RequestParam("idUsuario") String idUsuario){

        if(!consumoRepository.findByIdreserva(id).isEmpty()){
            model.addAttribute("causa", "EXISTEN CONSUMOS ASOCIADOS A ESTA RESERVA");
            return "error.html";
        }

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

    @GetMapping("/RF5")
    String mostrar(Model model){
        model.addAttribute("datos", reservaHabitacionRepository.findAll());
        return "RF5.html";
    }

    @GetMapping("/RF5/{id}/edit")
    String edit(Model model, @PathVariable("id") String id){
        model.addAttribute("id", id);

        return "/Formularios/RF5A.html";

    }

    @GetMapping("/RF5/{id}/fecha")
    String fecha(Model model, @PathVariable("id") String id, @RequestParam("fechaCheckIn") String fechaCheckIn){

        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);

        for(ReservaHabitacion res: habitacion.getReservasHabitaciones()){
            if(res.getId().equals(id)){
                res.setFechaCheckIn(fechaCheckIn);
                break;
            }
        }
        reservaHabitacion.setFechaCheckIn(fechaCheckIn);

        habitacionRepository.save(habitacion);
        reservaHabitacionRepository.save(reservaHabitacion);


        if(reservaHabitacion.getNumPersonas() <= 1){
            return "redirect:/RF5";
        }

        model.addAttribute("n", 0);
        model.addAttribute("id", id);
        model.addAttribute("max", reservaHabitacion.getNumPersonas());
        model.addAttribute("usuarios", usuarioRepository.findAll());

        return "/Formularios/RF5B.html";
    }

    @PostMapping("/RF5/{id}/usuarios/{n}")
    String putUsuario(Model model, @PathVariable("id") String id, @PathVariable("n") int n, @RequestParam("max") int max,
    @RequestParam("nombre") String nombre,@RequestParam("tipoDocumento") String tipoDocumento, @RequestParam("numeroDocumento") int numeroDocumento,
    @RequestParam("correoElectronico") String correoElectronico, @RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("contrasena") String contrasena,
    @RequestParam("usuario") String usuario){

        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();

        if(!usuario.equals("--")){
            Usuario usuario_ = usuarioRepository.findById(usuario).get();
            reservaHabitacion.getUsuarios().add(usuario_);
            reservaHabitacionRepository.save(reservaHabitacion);
        }
        else{
            Usuario usuario_ = new Usuario(null, nombre, new TipoUsuario("Cliente", "D"), tipoDocumento, numeroDocumento, correoElectronico, nombreUsuario, contrasena, new ArrayList<>(),null);
            usuarioRepository.insert(usuario_);
            reservaHabitacion.getUsuarios().add(usuario_);
            reservaHabitacionRepository.save(reservaHabitacion);
        }

        n++;

        if(n == max-1){
            Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);
            for(int i = 0; i< habitacion.getReservasHabitaciones().size();i++){
                if(habitacion.getReservasHabitaciones().get(i).getId().equals(id)){
                    habitacion.getReservasHabitaciones().set(i, reservaHabitacion);
                    break;
                }
            }
            habitacionRepository.save(habitacion);
            return "redirect:/RF5";
        }

        model.addAttribute("n", n);
        model.addAttribute("id", id);
        model.addAttribute("max", reservaHabitacion.getNumPersonas());
        model.addAttribute("usuarios", usuarioRepository.findAll());


        return "Formularios/RF5B.html";

    }

    @GetMapping("RF5/{id}/delete")
    String borrar(@PathVariable("id") String id){

        
        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);
        reservaHabitacion.setFechaCheckIn(null);
        reservaHabitacion.setUsuarios(reservaHabitacion.getUsuarios().subList(0, 1));

        for(int i = 0; i < habitacion.getReservasHabitaciones().size();i++){
            if(habitacion.getReservasHabitaciones().get(i).getId().equals(id)){
                habitacion.getReservasHabitaciones().set(i, reservaHabitacion);
                break;
            }
        }
        habitacionRepository.save(habitacion);
        reservaHabitacionRepository.save(reservaHabitacion);

        return "redirect:/RF5";
    }

    @GetMapping("/RF7")
    String out(Model model){

        model.addAttribute("datos", reservaHabitacionRepository.findAll());
        return "RF7.html";
    }

    @GetMapping("/RF7/{id}/edit")
    String outEdit(Model model, @PathVariable("id") String id){
        model.addAttribute("id", id);
        return "Formularios/RF7A.html";
    }

    @PostMapping("RF7/{id}/checkOut")
    String checkout(Model model, @PathVariable("id") String id, @RequestParam("fechaCheckOut") String fechaCheckOut) throws ParseException{
        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date inicio = sdf.parse(reservaHabitacion.getFechaInicio());
        Date fin = sdf.parse(reservaHabitacion.getFechaFin());

        long dias = (fin.getTime()-inicio.getTime()) /(24 * 60 * 60 * 1000);

        long dinero = dias * habitacion.getCostoAlojamiento();
        List<String> usuarios = new ArrayList<>();
        for(Usuario usuario: reservaHabitacion.getUsuarios()){
            usuarios.add(usuario.getId());
        }
        List<Consumo> consumos = consumoRepository.findByUsuarios(usuarios);

        for(Consumo consumo: consumos){
            dinero += consumo.getSumaTotal();
        }

        if(reservaHabitacion.getPlanConsumo() != null){
            dinero *= (1-planConsumoRepository.findById(reservaHabitacion.getPlanConsumo()).get().getDescuento());
        }

        model.addAttribute("dinero", dinero);
        model.addAttribute("fechaCheckOut", fechaCheckOut);
        model.addAttribute("id", id);
        model.addAttribute("consumos", consumos);
        model.addAttribute("reservaHabitacion", reservaHabitacion);
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("dias", dias);        

        return "Formularios/RF7B.html";
    }

    @GetMapping("RF7/{id}/confirmado")
    String con(Model model, @PathVariable("id") String id, @RequestParam("fechaCheckOut") String fechaCheckOut){

        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        reservaHabitacion.setFechaCheckOut(fechaCheckOut);

        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);
        for(int i  = 0; i < habitacion.getReservasHabitaciones().size();i++){
            if(habitacion.getReservasHabitaciones().get(i).getId().equals(id)){
                habitacion.getReservasHabitaciones().set(i, reservaHabitacion);
                break;
            }
        }

        reservaHabitacionRepository.save(reservaHabitacion);
        habitacionRepository.save(habitacion);

        return "redirect:/RF7";
    }

    @GetMapping("RF7/{id}/delete")
    String delete(@PathVariable("id") String id){
        ReservaHabitacion reservaHabitacion = reservaHabitacionRepository.findById(id).get();
        Habitacion habitacion = habitacionRepository.findByReservasHabitacionesId(id);

        reservaHabitacion.setFechaCheckOut(null);

        for(int i  = 0; i < habitacion.getReservasHabitaciones().size();i++){
            if(habitacion.getReservasHabitaciones().get(i).getId().equals(id)){
                habitacion.getReservasHabitaciones().set(i, reservaHabitacion);
                break;
            }
        }

        reservaHabitacionRepository.save(reservaHabitacion);
        habitacionRepository.save(habitacion);

        return "redirect:/RF7";
    }

}

