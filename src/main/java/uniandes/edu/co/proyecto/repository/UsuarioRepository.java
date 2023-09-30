package uniandes.edu.co.proyecto.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET usuario = :usuario, contraseña = :contraseña, nombre = :nombre, numDocumento = :numDocumento, email = :email, tipoDocumento = :tipoDocumento, tipoUsuario_id = :tipoUsuario_id WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, @Param("usuario") String usuario, @Param("contraseña") String contraseña, @Param("nombre") String nombre, @Param("numDocumento") long numDocumento,
                         @Param("email") String email, @Param("tipoDocumento") String tipoDocumento, @Param("tipoUsuario_id") Integer tipoUsuario_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, usuario, contraseña, nombre, numDocumento, email, tipoDocumento, tipoUsuario_id) VALUES ( usuariossecuencia.nextval , :usuario, :contraseña, :nombre, :numDocumento, :email, :tipoDocumento, :tipoUsuario_id)", nativeQuery = true)
    void insertarUsuario(@Param("usuario") String usuario, @Param("contraseña") String contraseña, @Param("nombre") String nombre, @Param("numDocumento") long numDocumento,
                             @Param("email") String email, @Param("tipoDocumento") String tipoDocumento, @Param("tipoUsuario_id") Integer tipoUsuario_id);

    @Query(value = "SELECT * FROM Usuarios  WHERE (contraseña = :contraseña AND usuario = :usuario)", nativeQuery = true)
    Usuario encontrarUsuarioPorUsuarioYContraseña(@Param("usuario") String usuario, @Param("contraseña") String contraseña);

}
