package com.goldenkids.springboot.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldenkids.springboot.web.app.models.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    public Usuario findByNombreUsuario(String nombrUsuario);
}
