package com.example.mdbspringboot.Modelo;

public class TipoUsuario {

    private String nombre;

    private String permisos;

    public TipoUsuario(String nombre, String permisos) {
        this.nombre = nombre;
        this.permisos = permisos;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    
}
