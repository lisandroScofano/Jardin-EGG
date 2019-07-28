package com.goldenkids.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldenkids.springboot.web.app.models.Alumno;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	@RequestMapping("/listaralumnos")
	public String listar(@RequestParam(required = false) String q,String error, Model modelo) {

		

		return "alumno-listado";
	}
	
	
	@GetMapping("/formulario")
	public String abrirAlumno(ModelMap modelMap) {

		Alumno alumno = new Alumno();
		
		modelMap.put("alumno", alumno);
		modelMap.put("accion", "crear");

		return "alumno-admin";

	}

}
