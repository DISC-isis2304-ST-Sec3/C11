package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.TipoUsuario;
import uniandes.edu.co.proyecto.repository.TipoUsuarioRepository;

@Controller
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/tiposUsuarios")
    public String tipoUsuarioList(Model model) {
        model.addAttribute("tiposUsuarios", tipoUsuarioRepository.darTiposUsuarios());
        return "tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/new")
    public String tipoUsuarioForm(Model model) {
        model.addAttribute("tipoUsuario", new TipoUsuario());
        return "/fragments/tiposusuariosForm";
    }

    @PostMapping("/tiposUsuarios/new/save")
    public String tipoUsuarioGuardar(@ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.insertarTipoUsuario(tipoUsuario.getNombre(), tipoUsuario.getPermisos());
        return "redirect:/tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/{id}/edit")
    public String tipoUsuarioEditarForm(@PathVariable("id") int id, Model model) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.darTipoUsuario(id);
        if (tipoUsuario != null) {
            model.addAttribute("tipoUsuario", tipoUsuario);
            return "/fragments/edittiposusuario";
        } else {    
            return "redirect:/tiposUsuarios";
        }
    }
    @PostMapping("/tiposUsuarios/{id}/edit/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.actualizarTipoUsuario(((long) id), tipoUsuario.getNombre(), tipoUsuario.getPermisos());
        return "redirect:/tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/{id}/delete")
    public String Eliminar(@PathVariable("id") long id) {
        tipoUsuarioRepository.eliminarTipoUsuario(id);
        return "redirect:/tiposUsuarios";
    }

    
}
