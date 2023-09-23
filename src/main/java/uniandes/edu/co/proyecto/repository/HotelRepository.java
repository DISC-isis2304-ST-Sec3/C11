package uniandes.edu.co.proyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.*;
import java.util.*;
public interface HotelRepository extends JpaRepository<Hotel,Integer>{

    public interface RespuestaInformacionHoteles {
        int getTOTAL_HOTELES();
        
}
    

    @Query(value = "SELECT * FROM hoteles", nativeQuery = true)
    Collection<Hotel> darHoteles();


    @Query(value = "SELECT * FROM hoteles WHERE id = :id", nativeQuery = true)
        Hotel darHotel(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hoteles WHERE id = :id", nativeQuery = true)
    void eliminarHotel(@Param("id") long id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE hoteles SET nombre = :nombre, direccion = :direccion, ciudad = :ciudad WHERE id = :id", nativeQuery = true)
    void actualizarHotel(@Param("id") long id, @Param("nombre") String nombre,
                        @Param("direccion") String direccion,
                        @Param("ciudad") String ciudad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hoteles (id, nombre, direccion, ciudad) VALUES ( parranderos_sequence.nextval , :nombre, :direccion, :ciudad)", nativeQuery = true)
    void insertarHotel(@Param("nombre") String nombre, @Param("direccion") String direccion,
                        @Param("ciudad") String string);

    @Query(value = "SELECT COUNT(*) AS TOTAL_HOTELES, \r\n" + //
    "FROM hoteles", nativeQuery = true)
    Collection<RespuestaInformacionHoteles> darInformacionHoteles();
}
