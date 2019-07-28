package com.goldenkids.springboot.web.app.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenkids.springboot.web.app.models.TipoPerfil;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio us;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void crearUsuario(String nombre, String apellido, String password, String mail, String telefono,
            String nombreUsuario, int dni, TipoPerfil tipoPerfil) {

        Usuario usuario = new Usuario();

        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setTipoPerfil(tipoPerfil);

        usuario.setFechaAlta(new Date());
        usuario.setFechaBaja(null);

        if (buscarUsuario(dni) == null) {
            em.persist(usuario);
        }

    }

    @Transactional
    public void modificarUsuario(String nombre, String apellido, String password, String mail, String telefono,
            String nombreUsuario, int dni, TipoPerfil tipoPerfil) {

        Usuario usuario = new Usuario();

        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setTipoPerfil(tipoPerfil);

        usuario.setFechaAlta(new Date());
        usuario.setFechaBaja(null);

        em.merge(usuario);

    }

    @Transactional
    public void eliminar(Integer dni) throws Exception {
        Usuario usuario = buscarUsuario(dni);
        us.delete(usuario);
    }

    @Transactional
    public void darDeBaja(Integer dni) throws Exception {

        Usuario usuarioBaja = em.find(Usuario.class, dni);

        usuarioBaja.setFechaBaja(new Date());

    }

    public Usuario buscarUsuario(Integer dni) {
        return em.find(Usuario.class, dni);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarUsuarios(String q) {
        return em.createQuery("SELECT c FROM Usuario c WHERE c.nombre LIKE :q OR c.dni LIKE :q OR c.apellido LIKE :q")
                .setParameter("q", "%" + q + "%").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarUsuarios() {
        return em.createQuery("SELECT c FROM Usuario c WHERE c.fechaBaja is null").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> buscarUsuariosEliminados() {
        return em.createQuery("SELECT c FROM Usuario c WHERE c.fechaBaja is not null").getResultList();
    }

}
