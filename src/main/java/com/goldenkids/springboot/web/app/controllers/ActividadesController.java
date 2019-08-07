package com.goldenkids.springboot.web.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.Docente;
import com.goldenkids.springboot.web.app.models.Salita;
import com.goldenkids.springboot.web.app.models.TipoActividad;
import com.goldenkids.springboot.web.app.models.TipoCantidad;
import com.goldenkids.springboot.web.app.models.TipoPanial;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.services.ActividadService;
import com.goldenkids.springboot.web.app.services.AlumnoService;
import com.goldenkids.springboot.web.app.services.DocenteService;
import com.goldenkids.springboot.web.app.services.UsuarioService;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private AlumnoService alumnoService;

    Logger log = LoggerFactory.getLogger(ActividadesController.class);

    @GetMapping("/tipousuario")
    public String redireccionaSegunUsuario(Authentication authenticated) {
        //Usuario usuario = usuarioService.buscarUsuarioPorUserName(authenticated.getName());
        String tipoUsuario = authenticated.getAuthorities().toString();
        log.info("El tipo de usuario logueado para ver actividades es: " + tipoUsuario);

        if (tipoUsuario.equals("[DIRECTIVO]")) {
            return "redirect:/actividades/directivo";
        }
        if (tipoUsuario.equals("[PADRE]")) {
            return "redirect:/actividades/padre";
        }
        if (tipoUsuario.equals("[DOCENTE]")) {
            return "redirect:/actividades/docente/plantilla";
        }
        return null;
    }

    @GetMapping("/docente/plantilla")
    public String plantilla(Authentication authenticated, Model modelo) {

        Usuario usuario = usuarioService.buscarUsuarioPorUserName(authenticated.getName());

        Docente docente = docenteService.buscarDocentePorUsuario(usuario);
        Salita salita = docente.getSalita();
        String tipoDocente = docente.getTipoDocente().toString();

        if (tipoDocente.equals("AUXILIAR")) {
            return "error_403";
        }

        if (tipoDocente.equals("REEMPLAZANTE")) {
            return "error_403";
        }

        modelo.addAttribute("titulo", "Listado de alumnos");
        List<Alumno> alumnos;

        alumnos = actividadService.buscarAlumnnosPorSala(salita);

        modelo.addAttribute("nombreSalita", salita.getNombre());
        modelo.addAttribute("alumnos", alumnos);
        modelo.addAttribute("tituloPagina", "Actividades alumnos salita " + salita.getNombre());
        modelo.addAttribute("subtituloPagina", "Utilice este modulo para cargar las Actividades de los alumnos de la salita " + salita.getNombre());

        return "actividades-sala";
    }

    @GetMapping("/registraactividad")
    public String guardarActividad(@RequestParam(required = false) TipoActividad tipoActividad,
            @RequestParam(required = false) Integer cantidadLeche,
            @RequestParam(required = false) TipoCantidad tipoCantidad,
            @RequestParam(required = false) TipoPanial tipoPanial, @RequestParam(required = false) String observacion,
            @RequestParam(required = false) int dni, ModelMap modelMap) {
        log.info("El dni del alumno es:" + dni);
        try {
            actividadService.crearActividad(tipoActividad, cantidadLeche, tipoCantidad, tipoPanial, observacion, dni);
        } catch (Exception e) {
            e.getMessage();
        }

        return "redirect:/actividades/tipousuario";
    }

    @GetMapping("/actividades/docente/alumno")
    public String verDetalleAlumno(@RequestParam(required = true) Integer dni) {
        Alumno alumno = alumnoService.buscarAlumno(dni);

        return null;
    }

}
