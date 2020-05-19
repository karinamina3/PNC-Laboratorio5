package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;

@Controller
public class MainController {

	@Autowired
	private EstudianteDAO estudianteDAO;
	
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
				estudianteDAO.insert(e);
				
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
			estudiantes = estudianteDAO.findAll();
			
		} catch (Exception exception){
			exception.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
}
