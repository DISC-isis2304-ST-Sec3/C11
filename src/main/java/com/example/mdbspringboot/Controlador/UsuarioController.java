package com.example.mdbspringboot.Controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.Usuario;
import com.example.mdbspringboot.Repositorio.ServicioRepository;
import com.example.mdbspringboot.Repositorio.UsuarioRepository;
import com.example.mdbspringboot.Repositorio.UsuarioRepository.respuestaRFC3;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<respuestaRFC3> datos = usuarioRepository.RFC3(sdf.parse(fechaInicio), sdf.parse(fechaFin), idUsuario);
            for(respuestaRFC3 res: datos){
                res.setServicio(servicioRepository.findById(res.getServicio()).get().getNombre());
                res.setId(usuarioRepository.findById(res.getId()).get().getNombre());
            }
            model.addAttribute("datos", datos);

            return "/RFC3.html";
        }
}
