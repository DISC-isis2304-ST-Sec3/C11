package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.ReservaServicio;

public interface ReservaServicioRepository extends MongoRepository<ReservaServicio, String>{
    
}
