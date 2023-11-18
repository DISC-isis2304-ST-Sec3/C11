package com.example.mdbspringboot.Modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservasHabitaciones")
public class ReservaHabitacion {
    
    @Id
    private String id;

    String planConsumo;
    List<Usuario> usuarios;

    String fechaCheckIn;
    String fechaCheckOut;
    int numPersonas;
    String fechaInicio;
    String fechaFin;

    public ReservaHabitacion(String id, String planConsumo, List<Usuario> usuarios, String fechaCheckIn,
            String fechaCheckOut, int numPersonas, String fechaInicio, String fechaFin) {
        this.id = id;
        this.planConsumo = planConsumo;
        this.usuarios = usuarios;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.numPersonas = numPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanConsumo() {
        return planConsumo;
    }

    public void setPlanConsumo(String planConsumo) {
        this.planConsumo = planConsumo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(String fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public String getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(String fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    

}
