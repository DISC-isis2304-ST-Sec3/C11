package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.TipoHabitacion;

public interface TipoHabitacionRepository extends MongoRepository<TipoHabitacion,String>{
    
}
