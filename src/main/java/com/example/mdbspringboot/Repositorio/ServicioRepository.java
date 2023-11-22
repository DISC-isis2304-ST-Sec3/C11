package com.example.mdbspringboot.Repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mdbspringboot.Modelo.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio,String> {
    
    public class respuestaRFC4{
        @Id
        String id;

        int numconsumos;

        public respuestaRFC4(String id, int numconsumos) {
            this.id = id;
            this.numconsumos = numconsumos;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNumconsumos() {
            return numconsumos;
        }

        public void setNumconsumos(int numconsumos) {
            this.numconsumos = numconsumos;
        }

       

        
    }

    @Aggregation(pipeline = {"{$unwind: '$usuarios'}","{$project: {idUsuario: '$usuarios._id',fechaConsumo: {$dateFromString: {dateString: '$usuarios.fechaConsumo',},},documento: '$usuarios.numeroDocumento',usuario: '$usuarios.nombreUsuario',nombre: '$usuarios.nombre'}}"
                    ,"{$match: {_id: ?0,fechaConsumo: {$gte: ?1},fechaConsumo: {$lte: ?2}}}"
                    ,"{$group: {_id: '$?3',numconsumos: {$count: {}}}}","{$sort: {?4: ?5,}}"})
    List<respuestaRFC4> RFC4(String id, Date fecha1, Date fecha2, String agrupacion, String ordenamiento, int orden);
}
