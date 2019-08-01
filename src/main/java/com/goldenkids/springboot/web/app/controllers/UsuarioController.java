package com.goldenkids.springboot.web.app.controllers;

import com.goldenkids.springboot.web.app.models.Docente;
import com.goldenkids.springboot.web.app.models.Salita;
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
import org.springframework.security.core.Authentication;

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
    public String listar(@RequestParam(required = false) String q, Model modelo, Authentication authentication) {

        modelo.addAttribute("titulo", "Listado de Usuarios: ");

        List<Usuario> usuarios;
        if (q != null) {
            usuarios = usuarioServicio.buscarUsuarios(q);
        } else {
            usuarios = usuarioServicio.buscarUsuarios();
        }

//        log.info("El Nombre del usuario logueado es: " + authentication.getName() + " y su ROL es : " + authentication.getPrincipal().toString());
        modelo.addAttribute("q", q);
        modelo.addAttribute("usuarios", usuarios);
        modelo.addAttribute("titulo", "Administracion de Usuarios");

        return "usuario-listado";
    }

    @RequestMapping("/listarusuarioseliminados")
    public String listarEliminados(Model modelo) {

        modelo.addAttribute("titulo", "Listado de Usuarios: ");

        List<Usuario> usuariosEliminados = usuarioServicio.buscarUsuariosEliminados();

        modelo.addAttribute("usuarios", usuariosEliminados);
        modelo.addAttribute("titulo", "Administracion de Usuarios");

        return "usuario-listado";
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@ModelAttribute("usuario") @RequestParam String nombre, String apellido, String password,
            String mail, String telefono, String nombreUsuario, int dni, TipoPerfil tipoPerfil, String accion, String selectSalitaId, TipoDocente selectTipoDocente) {
        ModelAndView modelo = new ModelAndView();
        if (accion.equals("crear")) {
            if (usuarioServicio.buscarUsuario(dni) == null) {
                usuarioServicio.crearUsuario(nombre, apellido, password, mail, telefono, nombreUsuario, dni, tipoPerfil);
                if (tipoPerfil.toString().equals("DOCENTE")) {
                    docenteService.crearDocente(usuarioServicio.buscarUsuario(dni), selectSalitaId, selectTipoDocente);
                }
                modelo.addObject("success", "El usuario ha sido guardado con éxito.");
            } else {
                modelo.addObject("error", "El usuario ingresado ya existe. Por favor revise el DNI.");
                log.error("Ya existe un usuario ingresado con ese DNI");
            }
        } else if (accion.equals("modificar")) {
            Usuario usuarioOriginal = usuarioServicio.buscarUsuario(dni);
            String perfilOriginal = usuarioOriginal.getTipoPerfil().toString();

            if ((perfilOriginal.equals("PADRE")) || (perfilOriginal.equals("DIRECTIVO")) && (tipoPerfil.toString().equals("DOCENTE"))) {//si cambio de padre o directivo a docente
                docenteService.modificarNoDocenteADocente(usuarioOriginal, selectSalitaId, selectTipoDocente);
            }
            if ((perfilOriginal.equals("DOCENTE")) && (tipoPerfil.toString().equals("DOCENTE"))) {//si cambio entre tipos de docente
                docenteService.modificarTipoDocente(usuarioOriginal, selectSalitaId, selectTipoDocente);
            }
            if ((perfilOriginal.equals("DOCENTE")) && ((tipoPerfil.toString().equals("PADRE")) || (tipoPerfil.toString().equals("DIRECTIVO")))) {// si cambia de docente a no docente
                docenteService.modificarDocenteANoDocente(usuarioOriginal, selectSalitaId, selectTipoDocente);
            }

            usuarioServicio.modificarUsuario(nombre, apellido, password, mail, telefono, nombreUsuario, dni, tipoPerfil);//puede cambiar entre padre y directivo
            modelo.addObject("success", "El usuario ha sido modificado con éxito.");
        }

        List<Usuario> usuarios = usuarioRepositorio.findAll();

        modelo.addObject("usuarios", usuarios);
        modelo.setViewName("usuario-listado.html");

        return modelo;
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer dni, ModelMap model) {

        if (dni != null) {
            Usuario usuario = usuarioServicio.buscarUsuario(dni);
            model.put("usuario", usuario);
            model.put("accion", "modificar");
// SI el usuario es docente y titular, busco su salita guardada
            if (usuario.getTipoPerfil().toString().equals("DOCENTE")) {
                model.put("docente", docenteService.buscarDocentePorUsuario(usuario));
                if (docenteService.buscarDocentePorUsuario(usuario).getTipoDocente().toString().equals("TITULAR")) {
                    Salita salitaDocente = salitaService.buscarSalita(docenteService.buscarDocentePorUsuario(usuario).getSalita().getId());
                    model.put("salitaDocente", salitaDocente);
                }
            }
            model.put("salitas", salitaService.buscarSalitas());
        } else {
            model.put("usuario", new Usuario());
            model.put("docente", new Docente());
            model.put("accion", "crear");
            model.put("salitas", salitaService.buscarSalitas());
        }

        return "usuario-admin";
    }

    @GetMapping("/formulario")
    public String abrirUsuario(ModelMap modelMap) {

        Usuario usuario = new Usuario();

        modelMap.put("usuario", usuario);
        modelMap.put("docente", new Docente());
        modelMap.put("accion", "crear");
        modelMap.put("salitas", salitaService.buscarSalitas());

        return "usuario-admin";

    }

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
