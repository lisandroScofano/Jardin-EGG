package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.Actividad;
import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.services.ActividadService;
import com.goldenkids.springboot.web.app.services.AlumnoService;
import com.goldenkids.springboot.web.app.services.UsuarioService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("actividades")
public class DirectivoController {

    org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ActividadService actividadService;

    @GetMapping("/directivo")
    public String bienvenida(Model modelo, Authentication authenticated) {

        Usuario usuario = usuarioService.buscarUsuarioPorUserName(authenticated.getName());

        List<Alumno> hijos = alumnoService.buscarAlumnos();

        modelo.addAttribute("hijos", hijos);
        modelo.addAttribute("tituloPagina", "Informacion relativa a los alumnos");

        return "padre-vista";
    }

    @GetMapping("directivo/cargar")
    public String cargarActividades(Model modelo, @RequestParam(required = false) String q) {

        List<Alumno> alumnos = null;
        if (q != null) {
            alumnos = alumnoService.buscarAlumnos(q);
        } else {
            alumnos = alumnoService.buscarAlumnos();
        }

        modelo.addAttribute("alumnos", alumnos);
        modelo.addAttribute("tituloPagina", "Cargar Actividades a los alumnos");

        return "actividades-sala";
    }

}
