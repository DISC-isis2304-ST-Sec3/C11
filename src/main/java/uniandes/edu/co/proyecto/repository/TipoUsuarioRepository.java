package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.TipoUsuario;


public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Integer> {

    @Query(value = "SELECT * FROM tiposUsuarios", nativeQuery = true)
    Collection<TipoUsuario> darTiposUsuarios();

    @Query(value = "SELECT * FROM tiposUsuarios WHERE id = :id", nativeQuery = true)
    TipoUsuario darTipoUsuario(@Param("id") long id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposUsuarios WHERE id = :id", nativeQuery = true)
    void eliminarTipoUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposUsuarios SET nombre = :nombre  WHERE id = :id", nativeQuery = true)
    void actualizarTipoUsuario(@Param("id") long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposUsuarios (id, nombre) VALUES ( tiposUsuariossecuencia.nextval , :nombre)", nativeQuery = true)
    void insertarTipoUsuario(@Param("nombre") String nombre);
    

    
}
