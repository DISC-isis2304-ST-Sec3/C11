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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.Consumo;
import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.ServicioRepository;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ServicioRepository servicioRepository;


    @PostMapping("/login")
    private String hola(Model model, @RequestParam("nombreusuario") String nombreusuario, @RequestParam("contrasena") String constrasena){
        Usuario usuario = usuarioRepository.findCredentials(nombreusuario,constrasena);
        if(usuario == null){
             return "/index.html";
        }
        else{
            model.addAttribute("usuario", usuario);
            model.addAttribute("permisos", usuario.getTipoUsuario().getPermisos());
            return "/menuOpciones.html";
        }
    }

    @GetMapping("/RFC3")
    String RFC3(Model model){

        model.addAttribute("usuarios", usuarioRepository.findAll());

        return "/Formularios/RFC3.html";
    }

    @GetMapping("RFC3/mostrar")
    String RFC3Mostrar(Model model, @RequestParam("idUsuario") String idUsuario, @RequestParam("fechaInicio") String fechaInicio,
        @RequestParam("fechaFin") String fechaFin) throws ParseException{
            List<Consumo> consumos = new ArrayList<>();

            Usuario usuario = usuarioRepository.findById(idUsuario).get();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse(fechaInicio);
            Date fin = sdf.parse(fechaFin);

            for(Consumo con: usuario.getConsumos()){
                Date fechaCon = sdf.parse(con.getFechaConsumo());
                if(fechaCon.after(inicio) && fechaCon.before(fin)){
                    con.setUsuario(usuario.getNombre());
                    con.setServicio(servicioRepository.findById(con.getServicio()).get().getNombre());
                    consumos.add(con);
                }
            }

            
            model.addAttribute("datos", consumos);

            return "/RFC3.html";
        }
}
