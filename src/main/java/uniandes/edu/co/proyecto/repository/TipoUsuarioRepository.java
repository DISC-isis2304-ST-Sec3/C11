package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.TipoUsuario;


public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario,Integer> {

    @Query(value = "SELECT * FROM tiposdeusuario", nativeQuery = true)
    Collection<TipoUsuario> darTiposUsuarios();

    @Query(value = "SELECT * FROM tiposdeusuario WHERE id = :id", nativeQuery = true)
    TipoUsuario darTipoUsuario(@Param("id") long id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposdeusuario WHERE id = :id", nativeQuery = true)
    void eliminarTipoUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposdeusuario SET nombre = :nombre, permisos = :permisos  WHERE id = :id", nativeQuery = true)
    void actualizarTipoUsuario(@Param("id") long id, @Param("nombre") String nombre, @Param("permisos") String permisos);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposdeusuario (id, nombre,permisos) VALUES ( tiposusuariossecuencia.nextval , :nombre, :permisos)", nativeQuery = true)
    void insertarTipoUsuario(@Param("nombre") String nombre, @Param("permisos") String permisos);
    

    
}
