package uniandes.edu.co.proyecto.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ReservaServicio;


public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, Integer>{
    
    @Query(value = "SELECT * FROM reservasservicios", nativeQuery = true)
    Collection<ReservaServicio> darReservasServicios();

    @Query(value = "SELECT * FROM reservasservicios WHERE id = :id", nativeQuery = true)
    ReservaServicio darReservaServicio(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservasservicios WHERE id = :id", nativeQuery = true)
    void eliminarReservaServicio(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservasservicios SET numpersonas = :numpersonas, fechainicio = :fechainicio, fechafin = :fechafin, usuarios_id = :usuarios_id, servicios_id = :servicios_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaServicio(@Param("id") long id, @Param("numpersonas") Integer numpersonas, @Param("fechainicio") Date fechainicio,@Param("fechafin") Date fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("servicios_id") Integer servicios_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservasservicios (id, numpersonas,fechainicio,fechafin,usuarios_id,servicios_id) VALUES ( reservasserviciossecuencia.nextval , :nombre, :numpersonas, :fechainicio, :fechafin, :usuarios_id, :servicios_id)", nativeQuery = true)
    void insertarReservaServicio(@Param("numpersonas") Integer numpersonas, @Param("fechainicio") Date fechainicio,@Param("fechafin") Date fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("servicios_id") Integer servicios_id);

    @Query(value = "SELECT r.id, r.numPersonas, r.fechaInicio, r.fechaFin, s.nombre, s.descripcion FROM reservasservicios r JOIN servicios s ON r.servicios_id = s.id WHERE r.usuarios_id = :usuarios_id", nativeQuery = true)
    ReservaServicio darReservaPorUsuario(@Param("usuarios_id") Integer usuarios_id);
}
