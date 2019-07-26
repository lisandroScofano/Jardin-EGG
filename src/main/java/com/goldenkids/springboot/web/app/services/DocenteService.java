/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenkids.springboot.web.app.services;

import com.goldenkids.springboot.web.app.models.Docente;
import com.goldenkids.springboot.web.app.models.Salita;
import com.goldenkids.springboot.web.app.models.TipoDocente;
import com.goldenkids.springboot.web.app.models.TipoPerfil;
import com.goldenkids.springboot.web.app.models.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author lisandroscofano
 */
@Service
public class DocenteService {

    Logger log = LoggerFactory.getLogger(DocenteService.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void crearDocente(Usuario usuario, String nombreSalita, TipoDocente tipoDocente) {

        Docente docente = new Docente();

        docente.setUsuario(usuario);
        docente.setSalita(buscarSalita(nombreSalita).get(0));
        docente.setTipoDocente(tipoDocente);
        
        log.info(nombreSalita);
        log.info(docente.getTipoDocente().toString());

        em.persist(docente);

    }

    public List<Salita> buscarSalita(String nombreSalita) {
        return em.createQuery("SELECT s FROM Salita s WHERE s.nombre = :q")
                .setParameter("q", nombreSalita).getResultList();
    }
}
