package uniandes.edu.co.proyecto.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.ReservaHabitacion;

public interface ReservaHabitacionRepository extends JpaRepository<ReservaHabitacion, Integer>   {

    @Query(value = "SELECT * FROM reservashabitaciones", nativeQuery = true)
    Collection<ReservaHabitacion> darReservasHabitaciones();

    @Query(value = "SELECT * FROM reservashabitaciones WHERE id = :id", nativeQuery = true)
    ReservaHabitacion darReservaHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservashabitaciones WHERE id = :id", nativeQuery = true)
    void eliminarReservaHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservashabitaciones SET numpersonas = :numpersonas, fechainicio = TO_DATE(:fechainicio, 'YYYY-MM-DD'), fechafin = TO_DATE(:fechafin, 'YYYY-MM-DD'), fechacheckin = TO_DATE(:fechainicio, 'YYYY-MM-DD'), fechacheckout = TO_DATE(:fechafin, 'YYYY-MM-DD') ,usuarios_id = :usuarios_id, habitaciones_id = :habitaciones_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaHabitacion(@Param("id") long id, @Param("numpersonas") Integer numpersonas, @Param("fechainicio") String fechainicio,@Param("fechafin") String fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("habitaciones_id") Integer habitaciones_id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservashabitaciones (id, numpersonas,fechainicio,fechafin,fechacheckin,fechacheckout,usuarios_id,habitaciones_id,planesdeconsumo_id) VALUES ( reservashabitacionessecuencia.nextval, :numpersonas,TO_DATE(:fechainicio, 'YYYY-MM-DD') , TO_DATE(:fechafin, 'YYYY-MM-DD'), TO_DATE(:fechainicio, 'YYYY-MM-DD') , TO_DATE(:fechafin, 'YYYY-MM-DD'), :usuarios_id, :habitaciones_id,:plannesdeconsumo_id)", nativeQuery = true)
    void insertarReservaHabitacion(@Param("numpersonas") Integer numpersonas, @Param("fechainicio") String fechainicio,@Param("fechafin") String fechafin,@Param("usuarios_id") Integer usuarios_id,@Param("habitaciones_id") Integer habitaciones_id, @Param("plannesdeconsumo_id") Integer plannesdeconsumo_id);

    @Query(value = "Select * from reservashabitaciones where usuarios_id = :id", nativeQuery = true)
    Collection<ReservaHabitacion> darReservasHabitacionesUsuario(@Param("id") long id);

    @Query(value = "SELECT *FROM reservashabitaciones WHERE HABITACIONES_ID = :idHabitacion AND FECHAFIN >= TO_DATE(:fechainicio, 'YYYY-MM-DD') AND FECHAINICIO <= TO_DATE(:fechaFin, 'YYYY-MM-DD')", nativeQuery = true)
    Collection<ReservaHabitacion> darReservasHabitacionesHabitacion(@Param("idHabitacion") long idHabitacion, @Param("fechainicio") String fechainicio, @Param("fechaFin") String fechaFin);

    @Query(value = "SELECT h.id,h.numero AS,COALESCE(ROUND(100 * SUM(NVL(r.fechafin, SYSDATE) - r.fechainicio) / 365, 2), 0) "+
            "FROM habitaciones h "+
            "LEFT JOIN reservashabitaciones r ON h.id = r.habitaciones_id AND r.fechainicio BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE "+
            "GROUP BY  h.id, h.numero "+
            "ORDER BY h.id",nativeQuery = true)
    List<Object[]> RFC3();

}
