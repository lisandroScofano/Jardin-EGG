package com.goldenkids.springboot.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldenkids.springboot.web.app.models.Usuario;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    public Usuario findByNombreUsuario(String nombreUsuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario=?1")
    public Usuario buscarUsuarioPorNombre(String nombre);
}
