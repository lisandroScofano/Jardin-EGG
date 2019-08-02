package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.Alumno;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldenkids.springboot.web.app.models.Autorizados;

import com.goldenkids.springboot.web.app.repository.AutorizadosRepositorio;
import com.goldenkids.springboot.web.app.services.AlumnoService;

import com.goldenkids.springboot.web.app.services.AutorizadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/autorizados")
public class AutorizadosController {

    @Autowired
    private AutorizadosService autorizadosServicio;

    @Autowired
    private AutorizadosRepositorio autorizadosRepositorio;

    @Autowired
    private AlumnoService alumnoService;

    Logger log = LoggerFactory.getLogger(AutorizadosController.class);

    @GetMapping("/formulario")
    public String listar(Model modelo) {

        Autorizados autorizados = new Autorizados();

        modelo.addAttribute("autorizados", autorizados);
        modelo.addAttribute("accion", "crear");
        modelo.addAttribute("listadoAlumnos", alumnoService.buscarAlumnos());

        return "autorizados-admin";

    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@RequestParam String nombre, String apellido, String telefono1,
            String telefono2, int dni, String parentesco, String accion, String error, Integer alumnoAutorizado) throws Exception {
        ModelAndView modelo = new ModelAndView();

        if (accion.equals("crear")) {
            autorizadosServicio.crearAutorizados(nombre, apellido, telefono1, telefono2, dni, parentesco, error, alumnoAutorizado);
            modelo.addObject("success", "La Autorizacion ha sido creada con éxito.");
        } else if (accion.equals("modificar")) {
            autorizadosServicio.modificarAutorizados(nombre, apellido, telefono1, telefono2, dni, parentesco, error, alumnoAutorizado);
            modelo.addObject("success", "La Autorizacion ha sido modificada con éxito.");
        }

        List<Autorizados> autorizados = autorizadosRepositorio.findAll();

        modelo.addObject("error", error);

        modelo.addObject("autorizados", autorizados);
        modelo.setViewName("autorizados-lista.html");

        return modelo;
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer dni, ModelMap model) {

        if (dni != null) {
            Autorizados autorizados = autorizadosServicio.buscarAutorizados(dni);
            model.put("autorizados", autorizados);
            model.put("accion", "modificar");
            model.put("alumnoGuardado", autorizados.getAlumno());//ver como ponerlo por defecto
            model.put("listadoAlumnos", alumnoService.buscarAlumnos());
        } else {
            model.put("autorizados", new Autorizados());
            model.put("accion", "crear");
            model.put("listadoAlumnos", alumnoService.buscarAlumnos());

        }

        return "autorizados-admin";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Integer dni) {

        try {
            autorizadosServicio.eliminar(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/autorizados/listarautorizados";

    }

    @RequestMapping("/listarautorizados")
    public String listar(@RequestParam(required = false) String q, String error, Model modelo) {

        modelo.addAttribute("titulo", "Listado de Autorizados: ");

        List<Autorizados> autorizados;
        if (q != null) {
            autorizados = autorizadosServicio.buscarAutorizados(q);
        } else {
            autorizados = autorizadosServicio.buscarAutorizados();
        }

        modelo.addAttribute("error", error);
        modelo.addAttribute("q", q);
        modelo.addAttribute("autorizados", autorizados);

        return "autorizados-lista";
    }

    @RequestMapping("/listarautorizadoseliminados")
    public String listarEliminados(Model modelo) {

        modelo.addAttribute("titulo", "Listado de Autorizados Eliminados : ");

        List<Autorizados> autorizadosEliminados = autorizadosServicio.buscarAutorizadosEliminados();

        modelo.addAttribute("autorizados", autorizadosEliminados);
        modelo.addAttribute("titulo", "Administracion de Autorizados");

        return "autorizados-lista";
    }

    @GetMapping("/baja")
    public String darBaja(@RequestParam Integer dni) {

        try {
            autorizadosServicio.darDeBaja(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/autorizados/listarautorizados";

    }

}
