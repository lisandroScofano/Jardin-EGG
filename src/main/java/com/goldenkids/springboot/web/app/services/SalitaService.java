/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenkids.springboot.web.app.services;

import com.goldenkids.springboot.web.app.models.Salita;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author lisandroscofano
 */
@Service
public class SalitaService {

    Logger log = LoggerFactory.getLogger(SalitaService.class);

    @PersistenceContext
    private EntityManager em;

    public Salita buscarSalita(String id) {
        return em.find(Salita.class, id);
    }

    public List<Salita> buscarSalitas() {
        List<Salita> salitas = new ArrayList<>();
        salitas = em.createQuery("SELECT s FROM Salita s").getResultList();
        return salitas;
    }
}
