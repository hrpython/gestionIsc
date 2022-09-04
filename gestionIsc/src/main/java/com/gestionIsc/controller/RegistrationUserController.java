package com.gestionIsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gestionIsc.model.User;
import com.gestionIsc.service.UserService;

@Controller
public class RegistrationUserController {
	@Autowired
	UserService userService;

	@ModelAttribute("user")
	public User user() {
		return new User();
	}

// post : enregistre mon nv user, get : voir ma page
	@PostMapping("/registrationUser")
	public String register(@ModelAttribute("user") User user) {
		User user1;
		user1 = userService.saveUser(user);
		if (user1 == null) {
			return ("/registrationUser");
		}
		return "redirect:login";
	}

	@GetMapping("/registrationUser")
	public String registration() {
		return "registrationUser";
	}
}
