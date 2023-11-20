package com.example.mdbspringboot.Repositorio;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Habitacion;

public interface HabitacionRepository extends MongoRepository<Habitacion,String> {
    
    @Query("{'reservasHabitaciones':{$elemMatch:{'_id':?0}}}")
    Habitacion findByReservasHabitacionesId(String id);

    @Query("{'numero': ?0}")
    Habitacion findByNumero(int numero);


    
}
