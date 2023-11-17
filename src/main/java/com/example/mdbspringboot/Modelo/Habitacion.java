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

    public Habitacion(String id, int numero, String tipoHabitacion, int costoAlojamiento,
            List<ReservaHabitacion> reservasHabitaciones) {
        this.id = id;
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.costoAlojamiento = costoAlojamiento;
        this.reservasHabitaciones = reservasHabitaciones;
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

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCostoAlojamiento() {
        return costoAlojamiento;
    }

    public void setCostoAlojamiento(int costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }

    public List<ReservaHabitacion> getReservasHabitaciones() {
        return reservasHabitaciones;
    }

    public void setReservasHabitaciones(List<ReservaHabitacion> reservasHabitaciones) {
        this.reservasHabitaciones = reservasHabitaciones;
    }

    

    
}
