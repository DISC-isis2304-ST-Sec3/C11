package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tiposusuarios")
public class TipoUsuario {
    
    @Id
    private String id;
}
