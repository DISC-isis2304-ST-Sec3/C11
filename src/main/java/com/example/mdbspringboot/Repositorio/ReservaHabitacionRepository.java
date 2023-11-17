package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.ReservaHabitacion;

public interface ReservaHabitacionRepository extends MongoRepository<ReservaHabitacion,String> {
    
}
