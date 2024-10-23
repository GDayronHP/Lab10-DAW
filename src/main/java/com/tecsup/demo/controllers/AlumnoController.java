package com.tecsup.demo.controllers;

import com.tecsup.demo.domain.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @RequestMapping(value = "/listarAlumno", method = RequestMethod.GET)
    public String listarAlumno(Model model) {
        model.addAttribute("alumnos", alumnoService.listar());
        return "listarAlumno";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/formAlumno")
    public String crearAlumno(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        return "formAlumno";
    }

    @RequestMapping(value="/formAlumno/{id}")
    public String editarAlumno(@PathVariable(value="id") String id, Map<String, Object> model) {
        Alumno alumno = null;
        if(id != null && !id.isEmpty()) {
            alumno = alumnoService.buscar(Integer.valueOf(id));
        } else {
            return "redirect:/listarAlumno";
        }
        model.put("alumno", alumno);
        return "formAlumno";
    }

    @RequestMapping(value = "/formAlumno", method = RequestMethod.POST)
    public String guardarAlumno(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
            return "formAlumno";
        }
        alumnoService.grabar(alumno);
        status.setComplete();
        return "redirect:listarAlumno";
    }

    @RequestMapping(value="/eliminarAlumno/{id}")
    public String eliminarAlumno(@PathVariable(value="id") String id) {
        if(id != null && !id.isEmpty()) {
            alumnoService.eliminar(Integer.parseInt(id));
        }
        return "redirect:/listarAlumno";
    }

    @RequestMapping(value="/verAlumno")
    public String verAlumno(Model model) {
        model.addAttribute("alumnos", alumnoService.listar());
        model.addAttribute("titulo", "Lista de alumnos");
        return "alumno/verAlumno";
    }
}
