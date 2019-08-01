package com.goldenkids.springboot.web.app.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.goldenkids.springboot.web.app.models.Actividad;
import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Docente;
import com.goldenkids.springboot.web.app.models.TipoActividad;
import com.goldenkids.springboot.web.app.models.TipoCantidad;
import com.goldenkids.springboot.web.app.models.TipoPanial;
import com.goldenkids.springboot.web.app.models.Usuario;

@Service
public class ActividadService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void crearActividad(TipoActividad tipoActividad, Integer cantidadLeche, TipoCantidad cantidad,
			TipoPanial tipoPanial, String observacion, int dni ) throws Exception {

		Actividad actividad = new Actividad();
		Alumno alumno =em.find(Alumno.class, dni);

		if (tipoActividad.equals(tipoActividad.ENTRADA)) {
			actividad.setTipoActividad(tipoActividad);
			actividad.setInicio(new Date());
			actividad.setAlumno(alumno);
			
			em.persist(actividad);
		} else {
			if (tipoActividad.equals(tipoActividad.SALIDA)) {
				actividad.setTipoActividad(tipoActividad);
				actividad.setFin(new Date());
				actividad.setAlumno(alumno);

				em.persist(actividad);
			} else {
				if (tipoActividad.equals(tipoActividad.DESPIERTO)) {
					actividad.setTipoActividad(tipoActividad);
					actividad.setAlumno(alumno);
					actividad.setInicio(new Date());

					em.persist(actividad);
				} else {
					if (tipoActividad.equals(tipoActividad.DORMIDO)) {
						actividad.setTipoActividad(tipoActividad);
						actividad.setAlumno(alumno);
						actividad.setInicio(new Date());

						em.persist(actividad);
					} else {
						if (tipoActividad.equals(tipoActividad.DESAYUNO)) {
							actividad.setTipoActividad(tipoActividad);
							actividad.setAlumno(alumno);
							actividad.setInicio(new Date());

							em.persist(actividad);
						} else {
							if (tipoActividad.equals(tipoActividad.ALMUERZO)) {
								actividad.setTipoActividad(tipoActividad);
								actividad.setAlumno(alumno);
								actividad.setInicio(new Date());

								em.persist(actividad);
							} else {
								if (tipoActividad.equals(tipoActividad.MERIENDA)) {
									actividad.setTipoActividad(tipoActividad);
									actividad.setAlumno(alumno);
									actividad.setInicio(new Date());

									em.persist(actividad);
								} else {

								}

							}

						}
					}

				}

			}

		}

	}

	
//	"SELECT a.dni, a.nombre, a.apellido, s.nombre FROM Alumno a LEFT JOIN a.Salita s LEFT JOIN s.Docente d WHERE d.usuarios = :q"
//	  WHERE a.dni = :q
//	"SELECT a FROM Alumno a WHERE a.salita = :q"
	
	@SuppressWarnings("unchecked")
	public List<Alumno> buscarAlumnnosPorSala(String q) {
		int dni = Integer.parseInt(q);

		return em.createQuery(
				"SELECT a FROM Alumno a, Docente d WHERE a.salita = d.salita AND d.usuarios.dni = :dni")
				.setParameter("dni", dni).getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Alumno> buscarAlumnnosPorSala() {
		return em.createQuery("SELECT a FROM Alumno a ").getResultList();

	}


}
