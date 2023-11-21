package com.example.mdbspringboot.Modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("habitaciones")
public class Habitacion {
    
    @Id
    private String id;

    int numero;
    int costoAlojamiento;

    String tipoHabitacion;
    List<ReservaHabitacion> reservasHabitaciones;
    List<Consumo> consumos;
    public Habitacion(String id, int numero, int costoAlojamiento, String tipoHabitacion,
            List<ReservaHabitacion> reservasHabitaciones, List<Consumo> consumos) {
        this.id = id;
        this.numero = numero;
        this.costoAlojamiento = costoAlojamiento;
        this.tipoHabitacion = tipoHabitacion;
        this.reservasHabitaciones = reservasHabitaciones;
        this.consumos = consumos;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getCostoAlojamiento() {
        return costoAlojamiento;
    }
    public void setCostoAlojamiento(int costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    public List<ReservaHabitacion> getReservasHabitaciones() {
        return reservasHabitaciones;
    }
    public void setReservasHabitaciones(List<ReservaHabitacion> reservasHabitaciones) {
        this.reservasHabitaciones = reservasHabitaciones;
    }
    public List<Consumo> getConsumos() {
        return consumos;
    }
    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    
    
}
