package com.example.mdbspringboot.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mdbspringboot.Modelo.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario,String> {

    @Query("{}")
    List<Usuario> findAll();

    @Query("{nombreUsuario: '?0', contrasena: '?0'}")
    Usuario findCredentials(String nombreUsuario, String constrasena);
    
}
