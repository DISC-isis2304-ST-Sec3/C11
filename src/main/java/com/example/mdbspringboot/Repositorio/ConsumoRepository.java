package com.example.mdbspringboot.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Consumo;


public interface ConsumoRepository extends MongoRepository<Consumo,String>{

    @Query("{'usuario._id': {$in: ?0}}")
    List<Consumo> findByUsuarios(List<String> usuarios);

    @Query("{'reservaHabitacion':?0}")
    List<Consumo> findByIdreserva(String id);

    @Query("{'servicio':?0}")
    List<Consumo> findByIdServicio(String id);
}
