package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String login(@RequestParam("usuario") String username, @RequestParam("password") String password) {
        Usuario usuario = usuarioRepository.encontrarUsuarioPorUsuarioYContraseña(username, password);
        if (usuario != null) {
            String userType = usuario.getTipoUsuario().getNombre().toLowerCase();
            switch (userType) {
                case "gerente":
                    return "gerente";
                case "empleado":
                    return "empleado";
                case "recepcionista":
                    return "recepcionista";
                case "cliente":
                    return "cliente";
                default:
                    return "redirect:/error";
            }
        } else {
            return "index";
        }
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario( usuario.getUsuario(), usuario.getContrasena(), usuario.getNombre(), usuario.getNumDocumento(), usuario.getEmail(), usuario.getTipoDocumento(), usuario.getTipoUsuario().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }


    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") long id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(((long) id), usuario.getUsuario(), usuario.getContrasena(), usuario.getNombre(), usuario.getNumDocumento(), usuario.getEmail(), usuario.getTipoDocumento(), usuario.getTipoUsuario().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioEliminar(@PathVariable("id") long id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    
    
}
