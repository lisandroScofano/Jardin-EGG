/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenkids.springboot.web.app.services;

import com.goldenkids.springboot.web.app.models.Docente;
import com.goldenkids.springboot.web.app.models.TipoDocente;
import com.goldenkids.springboot.web.app.models.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lisandroscofano
 */
@Service
public class DocenteService {
    
    Logger log = LoggerFactory.getLogger(DocenteService.class);
    
    @Autowired
    private SalitaService salitaService;
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void crearDocente(Usuario usuario, String salitaId, TipoDocente tipoDocente) {
        
        Docente docente = new Docente();
        
        docente.setUsuario(usuario);
        docente.setSalita(salitaService.buscarSalita(salitaId));
        docente.setTipoDocente(tipoDocente);
        log.info(salitaId);
        log.info(docente.getSalita().getNombre());
        log.info(docente.getTipoDocente().toString());
        log.info(docente.getUsuario().getApellido());
        
        em.persist(docente);
        
    }
    
}
