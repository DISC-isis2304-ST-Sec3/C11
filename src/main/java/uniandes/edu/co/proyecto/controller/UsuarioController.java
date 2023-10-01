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
import uniandes.edu.co.proyecto.repository.TipoUsuarioRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @PostMapping("/login")
    public String login(Model modelo,@RequestParam("usuario") String username, @RequestParam("password") String password) {
        Usuario usuario = usuarioRepository.encontrarUsuarioPorUsuarioYcontrasena(username, password);
        if (usuario != null) {
            modelo.addAttribute("nombre", usuario.getNombre());
            modelo.addAttribute("permisos", usuario.getTipoUsuario().getPermisos());
            return "usuario";
        } else {
            return "index";
        }
    }

    @GetMapping("/usuarios")
    public String usuarios(Model modelo) {
        modelo.addAttribute("usuarios", usuarioRepository.darUsuarios());
        
        return "usuariosAPP";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposusuarios", tipoUsuarioRepository.darTiposUsuarios());
        return "/fragments/usuarioForm";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario( usuario.getNombreUsuario(), usuario.getContrasena(), usuario.getNombre(), usuario.getNumDocumento(), usuario.getEmail(), usuario.getTipoDocumento(), usuario.getTipoUsuario().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposusuarios", tipoUsuarioRepository.darTiposUsuarios());
            return "/fragments/editusuarios";
        } else {
            return "redirect:/usuarios";
        }
    }


    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") long id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(((long) id), usuario.getNombreUsuario(), usuario.getContrasena(), usuario.getNombre(), usuario.getNumDocumento(), usuario.getEmail(), usuario.getTipoDocumento(), usuario.getTipoUsuario().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioEliminar(@PathVariable("id") long id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    
    
}
