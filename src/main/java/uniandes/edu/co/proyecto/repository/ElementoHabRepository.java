package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.*;

public interface ElementoHabRepository extends JpaRepository<ElementoHab,Integer>{

    public interface RespuestaInformacionElementosHab {

        int getTOTAL_ELEMENTOS();
    }
    @Query(value = "SELECT * FROM elementoshab", nativeQuery = true)
    Collection<ElementoHab> darElementosHab();

    @Query(value = "SELECT * FROM elementoshab WHERE id = :id", nativeQuery = true)
    ElementoHab darElementoHab(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM elementoshab WHERE id = :id", nativeQuery = true)
    void eliminarElementoHab(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE elementoshab SET nombre = :nombre  WHERE id = :id", nativeQuery = true)
    void actualizarElementoHab(@Param("id") long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO elementoshab (id, nombre) VALUES ( sequence.nextval , :nombre)", nativeQuery = true)
    void insertarElementoHab(@Param("nombre") String nombre);

    @Query(value = "SELECT COUNT(*) AS TOTAL, \r\n" + //
                        "FROM elementoshab", nativeQuery = true)
        Collection<RespuestaInformacionElementosHab> darInformacionElementosHabs();
    

}
