package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tiposhabitaciones")
public class TipoHabitacion {
    
    @Id
    private String id;
}
