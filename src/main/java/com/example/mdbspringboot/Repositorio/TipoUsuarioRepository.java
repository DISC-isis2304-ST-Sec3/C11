package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.TipoUsuario;

public interface TipoUsuarioRepository extends MongoRepository<TipoUsuario,String>{
    
}
