package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.TipoDocente;
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

import com.goldenkids.springboot.web.app.models.TipoPerfil;
import com.goldenkids.springboot.web.app.models.Usuario;
import com.goldenkids.springboot.web.app.repository.UsuarioRepositorio;
import com.goldenkids.springboot.web.app.services.DocenteService;
import com.goldenkids.springboot.web.app.services.SalitaService;
import com.goldenkids.springboot.web.app.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private SalitaService salitaService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @RequestMapping("/listarusuarios")
    public String listar(@RequestParam(required = false) String q, Model modelo) {

        modelo.addAttribute("titulo", "Listado de Usuarios: ");

        List<Usuario> usuarios;
        if (q != null) {
            usuarios = usuarioServicio.buscarUsuarios(q);
        } else {
            usuarios = usuarioServicio.buscarUsuarios();
        }

        modelo.addAttribute("q", q);
        modelo.addAttribute("usuarios", usuarios);

        return "usuario-listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") @RequestParam String nombre, String apellido, String password,
            String mail, String telefono, String nombreUsuario, int dni, TipoPerfil tipoPerfil, String accion, String selectSalitaId, TipoDocente selectTipoDocente) {
        ModelAndView modelo = new ModelAndView();

        if (accion.equals("crear")) {
            if (usuarioServicio.buscarUsuario(dni) == null) {
                usuarioServicio.crearUsuario(nombre, apellido, password, mail, telefono, nombreUsuario, dni, tipoPerfil);
            } else {
                modelo.addObject("error", "El usuario ya existe");
            }
        } else if (accion.equals("modificar")) {
            usuarioServicio.modificarUsuario(nombre, apellido, password, mail, telefono, nombreUsuario, dni, tipoPerfil);
        }

        log.info(tipoPerfil.toString());

        if (tipoPerfil.toString().equals("DOCENTE")) {
            docenteService.crearDocente(usuarioServicio.buscarUsuario(dni), selectSalitaId, selectTipoDocente);
        }

        List<Usuario> usuarios = usuarioRepositorio.findAll();

        modelo.addObject("usuarios", usuarios);

        return "redirect:/usuario/listarusuarios";
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer dni, ModelMap model) {

        if (dni != null) {
            Usuario usuario = usuarioServicio.buscarUsuario(dni);
            model.put("usuario", usuario);
            model.put("accion", "modificar");
            model.put("salitas", salitaService.buscarSalitas());
        } else {
            model.put("usuario", new Usuario());
            model.put("accion", "crear");
            model.put("salitas", salitaService.buscarSalitas());
        }

        return "usuario-admin";
    }

    @GetMapping("/formulario")
    public String abrirUsuario(ModelMap modelMap) {

        Usuario usuario = new Usuario();

        modelMap.put("usuario", usuario);
        modelMap.put("accion", "crear");
        modelMap.put("salitas", salitaService.buscarSalitas());

        return "usuario-admin";

    }

//    @GetMapping("/eliminar")
//    public String eliminar(@RequestParam Integer dni) {
//
//        try {
//            usuarioServicio.eliminar(dni);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/usuario/listarusuarios";
//
//    }
    @GetMapping("/baja")
    public String darBaja(@RequestParam Integer dni) {

        try {
            usuarioServicio.darDeBaja(dni);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/usuario/listarusuarios";

    }
}
