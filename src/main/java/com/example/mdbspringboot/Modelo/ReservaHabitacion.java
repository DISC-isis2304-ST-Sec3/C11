package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservashabitaciones")
public class ReservaHabitacion {
    
    @Id
    private String id;
}
