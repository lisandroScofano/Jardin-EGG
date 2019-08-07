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
import com.goldenkids.springboot.web.app.models.Salita;
import com.goldenkids.springboot.web.app.models.TipoActividad;
import com.goldenkids.springboot.web.app.models.TipoCantidad;
import com.goldenkids.springboot.web.app.models.TipoPanial;
import com.goldenkids.springboot.web.app.models.Usuario;
import java.util.Calendar;

@Service
public class ActividadService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void crearActividad(TipoActividad tipoActividad, Integer cantidadLeche, TipoCantidad tipoCantidad,
            TipoPanial tipoPanial, String observacion, int dni) throws Exception {
        
        Actividad actividad = new Actividad();
        Alumno alumno = em.find(Alumno.class, dni);
        
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
                            actividad.setCantidad(tipoCantidad);
                            actividad.setAlumno(alumno);
                            actividad.setInicio(new Date());
                            
                            em.persist(actividad);
                        } else {
                            if (tipoActividad.equals(tipoActividad.ALMUERZO)) {
                                actividad.setTipoActividad(tipoActividad);
                                actividad.setCantidad(tipoCantidad);
                                actividad.setAlumno(alumno);
                                actividad.setInicio(new Date());
                                
                                em.persist(actividad);
                            } else {
                                if (tipoActividad.equals(tipoActividad.MERIENDA)) {
                                    actividad.setTipoActividad(tipoActividad);
                                    actividad.setCantidad(tipoCantidad);
                                    actividad.setAlumno(alumno);
                                    actividad.setInicio(new Date());
                                    
                                    em.persist(actividad);
                                } else {
                                    if (tipoActividad.equals(tipoActividad.LECHE)) {
                                        actividad.setTipoActividad(tipoActividad);
                                        actividad.setCantidadLeche(cantidadLeche);
                                        actividad.setAlumno(alumno);
                                        actividad.setInicio(new Date());
                                        
                                        em.persist(actividad);
                                    } else {
                                        if (tipoActividad.equals(tipoActividad.PANIAL)) {
                                            actividad.setTipoActividad(tipoActividad);
                                            actividad.setTipoPanial(tipoPanial);
                                            actividad.setAlumno(alumno);
                                            actividad.setInicio(new Date());
                                            
                                            em.persist(actividad);
                                        } else {
                                            if (tipoActividad.equals(tipoActividad.OBSERVACION)) {
                                                actividad.setTipoActividad(tipoActividad);
                                                actividad.setObservacion(observacion);
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
                
            }
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public List<Alumno> buscarAlumnnosPorSala(Salita salita) {
        return em.createQuery("SELECT a FROM Alumno a WHERE a.salita = :salita")
                .setParameter("salita", salita).getResultList();
        
    }
    
    @SuppressWarnings("unchecked")
    public List<Actividad> buscarActividadesPorAlumno(Alumno alumno) {
        
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(fecha);
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        Date diaPosterior = calendar.getTime();
//        
//        System.out.println(diaPosterior);
        
        return em.createQuery("SELECT a FROM Actividad a WHERE a.alumno = :alumno")
//                .setParameter("fecha", fecha)
//                .setParameter("fechaPosterior", diaPosterior)
                .setParameter("alumno", alumno)
                .getResultList();
        
    }
    
}
