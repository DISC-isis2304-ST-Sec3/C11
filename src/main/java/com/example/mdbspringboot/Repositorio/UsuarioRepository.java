package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario,String> {
    
}
