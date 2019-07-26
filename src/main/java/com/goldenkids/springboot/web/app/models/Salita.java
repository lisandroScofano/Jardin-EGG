package com.goldenkids.springboot.web.app.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Salita {

	@Id
	@GeneratedValue(generator = "uuid")
	private String id;


	@ManyToOne
	private Docente docente;
	private String nombre;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSalida;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

}
