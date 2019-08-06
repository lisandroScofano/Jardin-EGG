package com.goldenkids.springboot.web.app.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.repository.AlumnoRepositorio;

@Service
public class PadreService {

	@Autowired
	private EntityManager em;

	@Autowired
	private AlumnoRepositorio ar;

	
	public Query buscarHijo(String q) {

		Integer dni = Integer.parseInt(q);

		Query alumnos = em.createQuery(
				"SELECT a FROM Alumno a WHERE a.contacto = :dni")
				.setParameter("dni", dni);

		return alumnos;

	}
}
