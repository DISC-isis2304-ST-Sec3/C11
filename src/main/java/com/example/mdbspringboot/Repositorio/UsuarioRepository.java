package com.example.mdbspringboot.Repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.mdbspringboot.Modelo.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,String> {

    @Query("{}")
    List<Usuario> findAll();

    @Query("{nombreUsuario: '?0', contrasena: '?0'}")
    Usuario findCredentials(String nombreUsuario, String constrasena);

    @Query("{'consumos':{$elemMatch:{'_id':?0}}}")
    Usuario findConsumosId(String idConsumo);

    public class respuestaRFC3{

        @Id
        String id;

        String fechaConsumo;
        String idConsumo;
        String servicio;
        String sumaTotal;
        public respuestaRFC3(String id, String fechaConsumo, String idConsumo, String servicio, String sumaTotal) {
            this.id = id;
            this.fechaConsumo = fechaConsumo;
            this.idConsumo = idConsumo;
            this.servicio = servicio;
            this.sumaTotal = sumaTotal;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getFechaConsumo() {
            return fechaConsumo;
        }
        public void setFechaConsumo(String fechaConsumo) {
            this.fechaConsumo = fechaConsumo;
        }
        public String getIdConsumo() {
            return idConsumo;
        }
        public void setIdConsumo(String idConsumo) {
            this.idConsumo = idConsumo;
        }
        public String getServicio() {
            return servicio;
        }
        public void setServicio(String servicio) {
            this.servicio = servicio;
        }
        public String getSumaTotal() {
            return sumaTotal;
        }
        public void setSumaTotal(String sumaTotal) {
            this.sumaTotal = sumaTotal;
        }

        
    }

    @Aggregation(pipeline = {"{$unwind: '$consumos'}","{$project:{fechaConsumo: {$dateFromString: {dateString: '$consumos.fechaConsumo'}},idConsumo: '$consumos._id',servicio: '$consumos.servicio', sumaTotal: '$consumos.sumaTotal'}}",
                    "{$match:{fechaConsumo: {$gte: ?0},fechaConsumo: {$lte: ?1}, _id : ?2}}"})
    List<respuestaRFC3> RFC3(Date fecha1, Date fecha2, String idUsuario);
}


