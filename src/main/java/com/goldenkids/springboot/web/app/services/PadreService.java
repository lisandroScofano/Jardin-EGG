package com.goldenkids.springboot.web.app.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.repository.AlumnoRepositorio;
import java.util.ArrayList;

@Service
public class PadreService {

    @Autowired
    private EntityManager em;

    @Autowired
    private AlumnoRepositorio ar;

    public List<Alumno> buscarHijos(Usuario padre) {
        List<Alumno> hijos = new ArrayList<Alumno>();

        hijos = em.createQuery("SELECT a FROM Alumno a WHERE a.contacto = :padre")
                .setParameter("padre", padre).getResultList();
        
        return hijos;
    }

}
