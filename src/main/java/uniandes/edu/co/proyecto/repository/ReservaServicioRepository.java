package uniandes.edu.co.proyecto.repository;


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
    @Query(value = "UPDATE reservasservicios SET numpersonas = :numpersonas, fechainicio = TO_DATE(:fechainicio, 'DD/MM/YYYY HH24:MI:SS'), fechafin = TO_DATE(:fechafin, 'DD/MM/YYYY HH24:MI:SS'), usuarios_id = :usuarios_id, servicios_id = :servicios_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaServicio(@Param("id") long id, @Param("numpersonas") Integer numpersonas, @Param("fechainicio") String fechainicio,@Param("fechafin") String fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("servicios_id") Integer servicios_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservasservicios (id, numpersonas,fechainicio,fechafin,usuarios_id,servicios_id) VALUES ( reservasserviciossecuencia.nextval , :numpersonas, TO_DATE(:fechainicio, 'DD/MM/YYYY HH24:MI:SS'), TO_DATE(:fechafin, 'DD/MM/YYYY HH24:MI:SS'), :usuarios_id, :servicios_id)", nativeQuery = true)
    void insertarReservaServicio(@Param("numpersonas") Integer numpersonas, @Param("fechainicio") String fechainicio,@Param("fechafin") String fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("servicios_id") Integer servicios_id);

    @Query(value = "select * from reservasservicios where usuarios_id = :usuarios_id", nativeQuery = true)
    ReservaServicio darReservaPorUsuario(@Param("usuarios_id") Integer usuarios_id);

    @Query(value = "SELECT *FROM reservasservicios WHERE servicios_id = :idServicio AND FECHAFIN >= TO_DATE(:fechainicio, 'DD/MM/YYYY HH24:MI:SS') AND FECHAINICIO <= TO_DATE(:fechaFin, 'DD/MM/YYYY HH24:MI:SS')", nativeQuery = true)
    Collection<ReservaServicio> darReservasServiciosPorId(@Param("idServicio") long idServicio, @Param("fechainicio") String fechainicio, @Param("fechaFin") String fechaFin);
}
