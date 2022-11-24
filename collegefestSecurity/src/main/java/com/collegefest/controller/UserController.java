package com.collegefest.controller;



import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collegefest.entity.Role;
import com.collegefest.entity.User;
import com.collegefest.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@RequestMapping("/user")
	public String home() {
		return "register";
	}


	
	@GetMapping("/registration")
	public String showAdmintForm(Model model) {
		User user = new User();
		Role role = new Role();
		
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		return "registrationForm";
	}
	
	@PostMapping("/saveUser")
	public String saveStudent(Model model, @ModelAttribute("user") User user, @ModelAttribute("role")Role role) {
		
		
		
		String username = user.getUsername();
		String email=user.getEmailAddress();
		String password = user.getPassword();		
		String encodedPassword = encoder().encode(password);
		user.setEmailAddress(email);
		user.addRole(role);
		user.setPassword(encodedPassword);
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/user";
	}
}
