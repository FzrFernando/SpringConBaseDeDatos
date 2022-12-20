package com.jacaranda.studentDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.studentDB.model.Student;
import com.jacaranda.studentDB.service.StudentService;



@Controller
public class StudentController {
	
	@Autowired
	private StudentService servicio;
	
	@GetMapping({"listStudent","/"})
	public String listStudent(Model model) {
		model.addAttribute("lista", servicio.getStudents());
		return "listStudents";
	}
	
	@GetMapping("addStudent")
	public String addStudent(Model model) {
		Student s = new Student();
		model.addAttribute("estudiante",s);
		return "addStudent";
	}
	
	@PostMapping("/addStudent/submit")
	public String studentSubmit(@Validated @ModelAttribute("estudiante") Student s,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addStudent";
		}
		else {
			servicio.add(s);
			return "redirect:/listStudent";
		}
	}
	
	@GetMapping("/delStudent")
	public String delStudent(Model model, 
			@RequestParam(name="id") Long id) {
		Student estudiante = servicio.getStudent(id);
		model.addAttribute("estudiante", estudiante);
		return "delStudent";
	}
	
	@PostMapping("/delStudent/submit")
	public String delStudentSubmit(@ModelAttribute("student") Student s) {
		servicio.delete(s);
		return "redirect:/listStudent";
	}
	
	@GetMapping("/updateStudent")
	public String updateStudent(Model model,
			@RequestParam(name="id") Long id) {
			Student estudiante=servicio.getStudent(id);
			model.addAttribute("estudiante", estudiante);	
		return "updateStudent";
	}
	
	@PostMapping("/updateStudent/submit")
	public String updateStudentSubmit(Model model,Student s) {
		servicio.update(s);
		return "redirect:/listStudent";
	}
	
//	@GetMapping("login")
//	public String login() {
//		return "login";
//	}
}
