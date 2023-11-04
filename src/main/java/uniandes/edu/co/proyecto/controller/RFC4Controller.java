package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ServicioRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class RFC4Controller {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository  servicioRepository;

    @GetMapping("/RFC4")
    private String form(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());

        return "RFCAUX/RFC4Form.html";
    }

    @GetMapping("/RFC4/mostrar")
    private String mostrar(Model model, @RequestParam(name = "fecha1") String fecha1,
    @RequestParam(name = "fecha2") String fecha2, @RequestParam(name = "tiposervicio") String tiposervicio,
    @RequestParam(name = "costo1") String costo1,@RequestParam(name = "costo2") String costo2, @RequestParam(name = "usuario_id") String usuario_id){        

        if(costo1.equals("") || costo2.equals("")){
            costo1 = null;
        }
        if(fecha1.equals("") || fecha2.equals("")){
            fecha1 = null;
        }
        if(tiposervicio.equals("")){
            tiposervicio = null;
        }
        if(usuario_id.equals("-1")){
            usuario_id = null;
        }

        model.addAttribute("datos", servicioRepository.RFC4(costo1,costo2,fecha1,fecha2,tiposervicio,usuario_id));
        return "RFC/serviciosConCaracteristicas.html";
    }
}
