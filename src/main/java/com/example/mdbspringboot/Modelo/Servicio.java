package com.example.mdbspringboot.Modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("servicios")
public class Servicio {
    
    @Id
    String id;

    String nombre;
    String descripcion;
    int costoPorUnidad;
    int unidad;
    String horario;
    String tipoServicio;
    int capacidad;

    List<Producto> productos;

    List<Usuario> usuarios;

    public Servicio(String id, String nombre, String descripcion, int costoPorUnidad, int unidad, String horario,
            String tipoServicio, int capacidad, List<Producto> productos, List<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoPorUnidad = costoPorUnidad;
        this.unidad = unidad;
        this.horario = horario;
        this.tipoServicio = tipoServicio;
        this.capacidad = capacidad;
        this.productos = productos;
        this.usuarios = usuarios;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCostoPorUnidad() {
        return costoPorUnidad;
    }

    public void setCostoPorUnidad(int costoPorUnidad) {
        this.costoPorUnidad = costoPorUnidad;
    }

    public int getUnidad() {
        return unidad;
    }

    public void setUnidad(int unidad) {
        this.unidad = unidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
}
