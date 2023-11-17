package com.example.mdbspringboot.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
public class Usuario {
    
    @Id
    private String id;

    private String nombre;

    private String tiposUsuario;

    private String tipoDocumento;

    private String numeroDocumento;

    private String correoElectronico;

    private String nombreUsuario;

    private String contrasena;


    
}
