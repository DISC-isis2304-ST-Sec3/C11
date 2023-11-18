package com.example.mdbspringboot.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.ReservaHabitacion;

public interface ReservaHabitacionRepository extends MongoRepository<ReservaHabitacion,String> {
    @Query("{'usuarios':{$elemMatch:{'_id':?0}}}")
    List<ReservaHabitacion> findUsuariosId(String idUser);
}
