package uniandes.edu.co.proyecto.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.RFC3;
import uniandes.edu.co.proyecto.modelo.RFC5;
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

    @Query(value = "select reservashabitaciones.habitaciones_id, sum (fechafin-fechainicio)/365 as ocupacion " + 
                    "from  reservashabitaciones " +
                    "where reservashabitaciones.fechainicio > TO_DATE('2022/01/01','yyyy/mm/dd') "+
                    "and reservashabitaciones.fechafin < TO_DATE('2022/12/31','yyyy/mm/dd') "+
                    "group by reservashabitaciones.habitaciones_id",nativeQuery = true)
    Collection<RFC3> RFC3();

    @Query(value = "Select  reservashabitaciones.usuarios_id, usuarios.nombre, sum (consumos.sumatotal) "+
                    "from reservashabitaciones "+
                    "inner join consumos on consumos.reservashabitaciones_id=reservashabitaciones.id "+
                    "inner join usuarios on reservashabitaciones.usuarios_id=usuarios.id "+
                    "where reservashabitaciones.fechainicio > TO_DATE('2023/01/01','yyyy/mm/dd' ) "+
                    "and reservashabitaciones.fechafin < current_date "+
                    "group by reservashabitaciones.usuarios_id,usuarios.nombre", nativeQuery =  true)
    Collection<RFC5> RFC5(@Param("usuario_id") Long usuario_id, @Param("fecha1") String fecha1, @Param("fecha2") String fecha2);
}
