package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.Actividad;
import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.services.ActividadService;
import com.goldenkids.springboot.web.app.services.AlumnoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldenkids.springboot.web.app.services.PadreService;
import com.goldenkids.springboot.web.app.services.UsuarioService;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("actividades")
public class PadreController {

    org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

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
    public String verDetalle(@RequestParam(required = true) Integer dni, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaSolicitada, Model modelo, Authentication authenticated) throws ParseException {

        Date fecha = null;
        if (fechaSolicitada != null) {
            log.info("La fecha pasada en String es: " + fechaSolicitada);
            fecha = fechaSolicitada;
        } else {
            fecha = new Date();
        }
        fecha = actividadService.fechaFormateadaParaJpql(fecha);
        Date diaPosterior = actividadService.diaPosteariorFormateadoParaJpql(fecha);

        Alumno alumno = alumnoService.buscarAlumno(dni);

        List<Actividad> actividades = actividadService.buscarActividadesPorAlumno(alumno, fecha, diaPosterior);

        modelo.addAttribute("actividades", actividades);
        modelo.addAttribute("tituloPagina", "Informacion relativa a sus hijos");
        modelo.addAttribute("dni", dni);

        return "detalle-alumno";
    }

}
