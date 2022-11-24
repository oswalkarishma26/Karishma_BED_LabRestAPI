package com.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collegefest.entity.Student;
import com.collegefest.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
//@RequiredArgsConstructor
public class MainController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/studentlist")
	public String showStudents(Model model) {
		List<Student> students = studentService.getStudentDetail();
		model.addAttribute("students", students);
		return "studentlist";
	}

	@GetMapping("/showForm")
	public String showStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "studentform";
	}
	
	@PostMapping("save")
	public String saveStudent(Model model, @ModelAttribute("student") Student student) {
		System.out.println(student);
		studentService.createStudentDetails(student);
		return "redirect:/studentlist";
	}
	
	@PutMapping("/save")
	public String saveUpdatedStudent(Model model, @ModelAttribute("student") Student student) {
		System.out.println(student);
		studentService.createStudentDetails(student);
		return "redirect:/studentlist";
	}
	
	@GetMapping("/edit/{id}")
	public String updateStudent(Model model,@PathVariable("id")int id) {
		Student sd = studentService.getStudentDetail(id);
		System.out.println(sd);
		model.addAttribute("student",sd);
		return "updateForm";
	}
	@GetMapping("/delete/{id}")
	public String deleteStudent(Model model,@PathVariable("id")int id) {
		studentService.deleteStudentDetail(id);
		return  "redirect:/studentlist";
	}

}
