package com.goldenkids.springboot.web.app.controllers;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goldenkids.springboot.web.app.models.Alumno;
import com.goldenkids.springboot.web.app.services.PadreService;

@Controller
@RequestMapping("/padre")
public class PadreController {

	@Autowired
	private PadreService padreServicio;

	@RequestMapping({ "/", "" })
	public String bienvenida(@RequestParam(required = true) String q, ModelMap modelMap) {

		ModelAndView model = new ModelAndView();
		
		Query alumnos;
		
		try {
			alumnos = padreServicio.buscarHijo(q);
			modelMap.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
		}
		
		String error = "Error al buscar el alumno";
		
		model.addObject("error", error);
		

		return "padre-vista";
	}

}
