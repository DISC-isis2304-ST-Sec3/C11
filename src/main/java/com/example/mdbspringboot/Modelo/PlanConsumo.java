package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("planesconsumo")
public class PlanConsumo {
    
    @Id
    private String id;
}
