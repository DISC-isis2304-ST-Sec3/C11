package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
public class Usuario {
    
    @Id
    private String id;

    private String nombre;

    private TipoUsuario tipoUsuario;

    private String tipoDocumento;

    private String numeroDocumento;

    private String correoElectronico;

    private String nombreUsuario;

    private String contrasena;

    public Usuario(String id, String nombre, TipoUsuario tipoUsuario, String tipoDocumento, String numeroDocumento,
            String correoElectronico, String nombreUsuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.correoElectronico = correoElectronico;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    

    
}
