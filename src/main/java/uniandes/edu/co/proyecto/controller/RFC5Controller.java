package uniandes.edu.co.proyecto.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import uniandes.edu.co.proyecto.repository.ConsumoRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class RFC5Controller {
    

    @Autowired
    private ConsumoRepository consumoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/RFC5")
    private String form(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "RFCAUX/RFC5Form";
    }

    @GetMapping("/RFC5/mostrar")
    private String mostrar(Model model, @RequestParam(name = "fecha1") String fecha1,
                            @RequestParam(name = "fecha2") String fecha2, 
                            @RequestParam(name = "usuario_id") Long usuarios_id)
    {
        List<Object[]> datos = consumoRepository.RFC5(usuarios_id, fecha1, fecha2);
        BigInteger total = BigInteger.ZERO ;
        for(int i = 0; i < datos.size(); i++){
            BigDecimal decimalValue = (BigDecimal) datos.get(i)[3];
            BigInteger intValue = decimalValue.toBigInteger();

            total = total.add(intValue);
        }
        model.addAttribute("datos", datos);
        model.addAttribute("total", total);

        return "RFC/consumoDeUsuarioEnFechas";

    }
}
