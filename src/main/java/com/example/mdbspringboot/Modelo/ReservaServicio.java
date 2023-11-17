package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservasServicios")
public class ReservaServicio {
    @Id
    private String id;   
}
