package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.PlanConsumo;

public interface PlanConsumoRepository extends MongoRepository<PlanConsumo,String>{
    
}
