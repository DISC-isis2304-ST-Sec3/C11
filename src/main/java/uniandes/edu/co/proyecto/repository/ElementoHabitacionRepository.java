package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.*;

public interface ElementoHabitacionRepository extends JpaRepository<ElementoHabitacion,Integer>{

    public interface RespuestaInformacionElementosHabitaciones {

        int getTOTAL_ELEMENTOS();
    }
    @Query(value = "SELECT * FROM elementoshabitaciones", nativeQuery = true)
    Collection<ElementoHabitacion> darElementosHabitaciones();

    @Query(value = "SELECT * FROM elementoshabitaciones WHERE id = :id", nativeQuery = true)
    ElementoHabitacion darElementoHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM elementoshabitaciones WHERE id = :id", nativeQuery = true)
    void eliminarElementoHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE elementoshabitaciones SET nombre = :nombre  WHERE id = :id", nativeQuery = true)
    void actualizarElementoHabitacion(@Param("id") long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO elementoshabitaciones (id, nombre) VALUES ( HOTELSECUENCIA.nextval , :nombre)", nativeQuery = true)
    void insertarElementoHabitacion(@Param("nombre") String nombre);

    @Query(value = "SELECT COUNT(*) AS TOTAL, \r\n" + //
                        "FROM elementoshabitaciones", nativeQuery = true)
        Collection<RespuestaInformacionElementosHabitaciones> darInformacionElementosHabitaciones();
    

}
