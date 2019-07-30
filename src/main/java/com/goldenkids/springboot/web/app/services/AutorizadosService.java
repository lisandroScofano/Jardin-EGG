package com.goldenkids.springboot.web.app.services;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenkids.springboot.web.app.models.Autorizados;

import com.goldenkids.springboot.web.app.repository.AutorizadosRepositorio;

@Service
public class AutorizadosService {

	@Autowired
	private AutorizadosRepositorio ar;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void crearAutorizados(String nombre, String apellido, String telefono1, String telefono2, int dni,
			String parentesco, String error) throws Exception {

		Autorizados autorizados = new Autorizados();

		autorizados.setApellido(apellido);
		autorizados.setNombre(nombre);
		autorizados.setDni(dni);
		autorizados.setTelefono1(telefono1);
		autorizados.setTelefono2(telefono2);
		autorizados.setParentesco(parentesco);

		if (buscarAutorizados(dni) == null) {
			em.persist(autorizados);
		} else {

			error = "Usuario ingresado no valido";

		}
	}

	@Transactional
	public void modificarAutorizados(String nombre, String apellido, String telefono1, String telefono2, int dni,
			String parentesco, String error) {

		Autorizados autorizados = new Autorizados();

		autorizados.setApellido(apellido);
		autorizados.setNombre(nombre);
		autorizados.setDni(dni);
		autorizados.setTelefono1(telefono1);
		autorizados.setTelefono2(telefono2);
		autorizados.setParentesco(parentesco);

		em.merge(autorizados);

	}

	public Autorizados buscarAutorizados(Integer dni) {
		return em.find(Autorizados.class, dni);
	}

	@SuppressWarnings("unchecked")
	public List<Autorizados> buscarAutorizados(String q) {
		return em.createQuery("SELECT c FROM Autorizados c WHERE c.nombre LIKE :q OR c.dni LIKE :q")
				.setParameter("q", "%" + q + "%").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Autorizados> buscarAutorizados() {
		return em.createQuery("SELECT c FROM Autorizados c ").getResultList();
	}

	@Transactional
	public void eliminar(Integer dni) throws Exception {
		Autorizados autorizados = buscarAutorizados(dni);
		ar.delete(autorizados);
	}



	
	
}
