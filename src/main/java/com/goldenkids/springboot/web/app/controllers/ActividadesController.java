package com.goldenkids.springboot.web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.models.TipoActividad;
import com.goldenkids.springboot.web.app.models.TipoCantidad;
import com.goldenkids.springboot.web.app.models.TipoPanial;
import com.goldenkids.springboot.web.app.services.ActividadService;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {

	@Autowired
	private ActividadService actividadService;

	@RequestMapping("/plantilla")
	public String plantilla(@RequestParam(required = false) String q , Model modelo) {
		
		modelo.addAttribute("titulo", "Listado de alumnos");
		List<Alumno> alumnos;
		
		if (q != null) {
			alumnos = actividadService.buscarAlumnnosPorSala(q);	
		} else {	
			alumnos = actividadService.buscarAlumnnosPorSala();
		}
		
		modelo.addAttribute("q", q);
		modelo.addAttribute("alumnos", alumnos);

		return "actividades-sala";
	}

	@RequestMapping("/registraactividad")
	public String guardarActividad(@RequestParam(required = false) TipoActividad tipoActividad, @RequestParam(required = false) Integer cantidadLeche,
			@RequestParam(required = false) TipoCantidad cantidad, @RequestParam(required = false) TipoPanial tipoPanial, @RequestParam(required = false) String observacion,
			@RequestParam(required = false) int dni, ModelMap modelMap) {
		
		try {
			actividadService.crearActividad(tipoActividad, cantidadLeche, cantidad, tipoPanial, observacion, dni);		
		} catch (Exception e){
			e.getMessage();
		}

//		return "redirect:/usuario/listarusuarios";
		return "redirect:/actividades/plantilla";
	}

}

