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
import com.goldenkids.springboot.web.app.services.AlumnoService;
import com.goldenkids.springboot.web.app.services.SalitaService;
import com.goldenkids.springboot.web.app.services.UploadService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    org.slf4j.Logger log = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    private SalitaService salitaService;

    @Autowired
    private AlumnoService alumnoServicio;

    @Autowired
    private UploadService uploadService;

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
        modelo.addAttribute("pagina", "Alumnos");
        modelo.addAttribute("tituloPagina", "Administración de Alumnos");
        modelo.addAttribute("subtituloPagina", "Utilice este modulo para administrar los registros de Alumnos del jardin.");

        return "alumno-listado";
    }

    @RequestMapping("/listaralumnoseliminados")
    public String listarEliminados(Model modelo) {

        modelo.addAttribute("titulo", "Listado de Alumnos Eliminados : ");

        List<Alumno> alumnosEliminados = alumnoServicio.buscarAlumnosEliminados();

        modelo.addAttribute("alumnos", alumnosEliminados);
        modelo.addAttribute("tituloPagina", "Administración de Alumnos");
        modelo.addAttribute("subtituloPagina", "Utilice este modulo para administrar los registros de Alumnos del jardin.");

        return "alumno-listado";
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@ModelAttribute("alumno") @RequestParam Integer dni, String nombre, String apellido,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, String accion, String selectSalitaId, MultipartFile file) {
        ModelAndView modelo = new ModelAndView();

        String uniqueFileName = uploadService.cargarArchivo(file);//cargo el archivo a la carpeta y devuelvo el nombre del archivo para persistir en la BD

        if (accion.equals("crear")) {
            alumnoServicio.crearAlumno(dni, nombre, apellido, fechaNacimiento, selectSalitaId, uniqueFileName);
            modelo.addObject("success", "El Alumno ha sido creado con éxito.");
        } else if (accion.equals("modificar")) {
            alumnoServicio.modificarAlumno(dni, nombre, apellido, fechaNacimiento, selectSalitaId, uniqueFileName);
            modelo.addObject("success", "El Alumno ha sido modificado con éxito.");
        }

        List<Alumno> alumnos = alumnoServicio.buscarAlumnos();

        modelo.addObject("alumnos", alumnos);
        modelo.setViewName("alumno-listado.html");

        return modelo;
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
        modelMap.put("tituloPagina", "Registro de Alumnos");

        return "alumno-admin";

    }

    @GetMapping("/baja")
    public String darBaja(@RequestParam Integer dni) {

        try {
            alumnoServicio.darDeBaja(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/alumno/listaralumnos";

    }

}
