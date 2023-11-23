package com.example.mdbspringboot.Repositorio;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Habitacion;

public interface HabitacionRepository extends MongoRepository<Habitacion,String> {
    
    @Query("{'reservasHabitaciones':{$elemMatch:{'_id':?0}}}")
    Habitacion findByReservasHabitacionesId(String id);

    @Query("{'numero': ?0}")
    Habitacion findByNumero(int numero);

    public class respuestaRFC1{
        @Id
        int id;
        int total;
        public respuestaRFC1(int id, int total) {
            this.id = id;
            this.total = total;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getTotal() {
            return total;
        }
        public void setTotal(int total) {
            this.total = total;
        }

        
    }
    @Aggregation(pipeline = {"{$unwind: '$consumos'}","{$project: {_id: '$numero',monto: '$consumos.sumaTotal',fechaConsumo: {$dateFromString: {dateString: '$consumos.fechaConsumo'}}}}","{$match: {fechaConsumo: {$gte: ?0,},fechaConsumo: {$lte: ?1}}}","{$group: {_id: '$_id',total: {$sum: '$monto'}}}"})
    List<respuestaRFC1> RFC1(Date fecha1, Date fecha2);

    public class respuestaRFC2{
        @Id
        String id;
        double total;
        public respuestaRFC2(String id, double total) {
            this.id = id;
            this.total = total;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public double getTotal() {
            return total;
        }
        public void setTotal(double total) {
            this.total = total;
        }
            
        
    }

    @Aggregation(pipeline = {"{$unwind:{path: '$reservasHabitaciones'}}", "{$project:{fechaInicio:{$dateFromString:{dateString:'$reservasHabitaciones.fechaInicio'}},fechaFin: {$dateFromString: {dateString:'$reservasHabitaciones.fechaFin'}},numero: '$numero'}}",
        "{$match:{fechaInicio: {$gte: ?0},fechaFin: {$lte:?1}}}","{$project:{_id: '$numero',cant: {$divide: [{$subtract: ['$fechaFin','$fechaInicio']}, 315360000]}}}","{$group:{_id: '$_id',total: {$sum: '$cant'}}}"})
    List<respuestaRFC2> RFC2(Date fecha1, Date fecha2);


    @Query("{'tipoHabitacion': ?0}")
    List<Habitacion> findByTipoHabitacion(String id);
    
}
