package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.Actividad;
import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.services.ActividadService;
import com.goldenkids.springboot.web.app.services.AlumnoService;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldenkids.springboot.web.app.services.PadreService;
import com.goldenkids.springboot.web.app.services.UsuarioService;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("actividades")
public class PadreController {

    @Autowired
    private PadreService padreServicio;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/padre")
    public String bienvenida(Model modelo, Authentication authenticated) {

        Usuario usuario = usuarioService.buscarUsuarioPorUserName(authenticated.getName());

        List<Alumno> hijos = padreServicio.buscarHijos(usuario);

        modelo.addAttribute("hijos", hijos);
        modelo.addAttribute("tituloPagina", "Informacion relativa a sus hijos");

        return "padre-vista";
    }

    @GetMapping("/padre/detalle")
    public String verDetalle(@RequestParam(required = true) Integer dni, @RequestParam(required = false) Date fechaSolicitada, Model modelo, Authentication authenticated) {

//        Date fecha = null;
//
//        if (fechaSolicitada != null) {
//            fecha = fechaSolicitada;
//        } else {
//            fecha = new Date();
//        }

        Alumno alumno = alumnoService.buscarAlumno(dni);

        List<Actividad> actividades = actividadService.buscarActividadesPorAlumno(alumno);
        
        modelo.addAttribute("actividades", actividades);
        modelo.addAttribute("tituloPagina", "Informacion relativa a sus hijos");

        return "detalle-alumno";
    }

}
