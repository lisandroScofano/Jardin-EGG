package com.goldenkids.springboot.web.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.repository.AlumnoRepositorio;
import com.goldenkids.springboot.web.app.services.AlumnoService;
import com.goldenkids.springboot.web.app.services.SalitaService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private SalitaService salitaService;

    @Autowired
    private AlumnoService alumnoServicio;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @RequestMapping("/listaralumnos")
    public String listar(@RequestParam(required = false) String q, String error, Model modelo) {

        List<Alumno> alumnos;
        if (q != null) {
            alumnos = alumnoServicio.buscarAlumnos(q);
        } else {
            alumnos = alumnoServicio.buscarAlumnos();
        }

        modelo.addAttribute("alumnos", alumnos);
        modelo.addAttribute("q", q);

        return "alumno-listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("alumno") @RequestParam Integer dni, String nombre, String apellido,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento, String accion, String selectSalitaId) {
        ModelAndView modelo = new ModelAndView();

        if (accion.equals("crear")) {
            alumnoServicio.crearAlumno(dni, nombre, apellido, fechaNacimiento, selectSalitaId);
            modelo.addObject("success", "El Alumno ha sido creado con éxito.");
        } else if (accion.equals("modificar")) {
            alumnoServicio.modificarAlumno(dni, nombre, apellido, fechaNacimiento, selectSalitaId);
            modelo.addObject("success", "El Alumno ha sido modificado con éxito.");
        }

        List<Alumno> alumnos = alumnoRepositorio.findAll();

        modelo.addObject("alumnos", alumnos);

        return "redirect:/alumno/listaralumnos";
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer dni, ModelMap model) {

        if (dni != null) {
            Alumno alumno = alumnoServicio.buscarAlumno(dni);
            model.put("alumno", alumno);
            model.put("accion", "modificar");
            model.put("salitas", salitaService.buscarSalitas());
            model.put("salitaActual", alumno.getSalita());
        } else {
            model.put("alumno", new Alumno());
            model.put("accion", "crear");
            model.put("salitas", salitaService.buscarSalitas());
        }

        return "alumno-admin";
    }

    @GetMapping("/formulario")
    public String abrirAlumno(ModelMap modelMap) {

        Alumno alumno = new Alumno();

        modelMap.put("alumno", alumno);
        modelMap.put("accion", "crear");
        modelMap.put("salitas", salitaService.buscarSalitas());

        return "alumno-admin";

    }

}
