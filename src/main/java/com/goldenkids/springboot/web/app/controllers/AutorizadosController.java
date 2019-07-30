package com.goldenkids.springboot.web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldenkids.springboot.web.app.models.Autorizados;

import com.goldenkids.springboot.web.app.repository.AutorizadosRepositorio;
import com.goldenkids.springboot.web.app.services.AlumnoService;

import com.goldenkids.springboot.web.app.services.AutorizadosService;

@Controller
@RequestMapping("/autorizados")
public class AutorizadosController {

    @Autowired
    private AutorizadosService autorizadosServicio;

    @Autowired
    private AutorizadosRepositorio autorizadosRepositorio;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/formulario")
    public String abrirAutorizados(ModelMap modelMap) {

        Autorizados autorizados = new Autorizados();

        modelMap.put("autorizados", autorizados);
        modelMap.put("accion", "crear");
        modelMap.put("alumnos", alumnoService.buscarAlumnos());

        return "autorizados-admin";

    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("autorizados") @RequestParam String nombre, String apellido, String telefono1,
            String telefono2, int dni, String parentesco, String accion, String error) throws Exception {
        ModelAndView modelo = new ModelAndView();

        if (accion.equals("crear")) {
            autorizadosServicio.crearAutorizados(nombre, apellido, telefono1, telefono2, dni, parentesco, error);
            modelo.addObject("success", "La Autorizacion ha sido creada con éxito.");
        } else if (accion.equals("modificar")) {
            autorizadosServicio.modificarAutorizados(nombre, apellido, telefono1, telefono2, dni, parentesco, error);
            modelo.addObject("success", "La Autorizacion ha sido modificada con éxito.");
        }

        List<Autorizados> autorizados = autorizadosRepositorio.findAll();

        modelo.addObject("error", error);

        modelo.addObject("autorizados", autorizados);

        return "redirect:/autorizados/listarautorizados";
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer dni, ModelMap model) {

        if (dni != null) {
            Autorizados autorizados = autorizadosServicio.buscarAutorizados(dni);
            model.put("autorizados", autorizados);
            model.put("accion", "modificar");
        } else {
            model.put("autorizados", new Autorizados());
            model.put("accion", "crear");
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

}
