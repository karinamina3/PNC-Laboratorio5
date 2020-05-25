package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {

	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		Estudiante e = new Estudiante();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante", e);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/insertar")
	public ModelAndView insertar(@Valid @ModelAttribute Estudiante e, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName("index");
		} else {
			try {
				estudianteService.save(e);
				
			} catch (Exception exeption) {
				exeption.printStackTrace();
			}
			
			mav.addObject("estudiante", new Estudiante());
			mav.setViewName("index");
		}
		
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listaEstudiantes() {
		ModelAndView mav = new ModelAndView();
	
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
			
		} catch (Exception exception){
			exception.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	
	@RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "code") int code){
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        try {
            estudianteService.delete(code);
            estudiantes = estudianteService.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
        return mav;
    }
}
