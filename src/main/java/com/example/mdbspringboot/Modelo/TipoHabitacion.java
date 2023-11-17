package com.example.mdbspringboot.Modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tiposHabitaciones")
public class TipoHabitacion {
    
    @Id
    private String id;

    List<ElementoHabitacion> elementosHabitaciones;

    String nombre;
    int capacidad;
    public TipoHabitacion(String id, List<ElementoHabitacion> elementosHabitaciones, String nombre, int capacidad) {
        this.id = id;
        this.elementosHabitaciones = elementosHabitaciones;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<ElementoHabitacion> getElementosHabitaciones() {
        return elementosHabitaciones;
    }
    public void setElementosHabitaciones(List<ElementoHabitacion> elementosHabitaciones) {
        this.elementosHabitaciones = elementosHabitaciones;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


    
}
