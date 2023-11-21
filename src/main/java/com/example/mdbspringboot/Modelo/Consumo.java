package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("consumos")
public class Consumo {
    
    @Id
    private String id;

    int sumaTotal;
    String fechaConsumo;
    int numConsumos;
    String descripcion;

    String servicio;
    String reservaHabitacion;
    String usuario;
    public Consumo(String id, int sumaTotal, String fechaConsumo, int numConsumos, String descripcion, String servicio,
            String reservaHabitacion, String usuario) {
        this.id = id;
        this.sumaTotal = sumaTotal;
        this.fechaConsumo = fechaConsumo;
        this.numConsumos = numConsumos;
        this.descripcion = descripcion;
        this.servicio = servicio;
        this.reservaHabitacion = reservaHabitacion;
        this.usuario = usuario;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getSumaTotal() {
        return sumaTotal;
    }
    public void setSumaTotal(int sumaTotal) {
        this.sumaTotal = sumaTotal;
    }
    public String getFechaConsumo() {
        return fechaConsumo;
    }
    public void setFechaConsumo(String fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
    public int getNumConsumos() {
        return numConsumos;
    }
    public void setNumConsumos(int numConsumos) {
        this.numConsumos = numConsumos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getServicio() {
        return servicio;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public String getReservaHabitacion() {
        return reservaHabitacion;
    }
    public void setReservaHabitacion(String reservaHabitacion) {
        this.reservaHabitacion = reservaHabitacion;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
    

}
