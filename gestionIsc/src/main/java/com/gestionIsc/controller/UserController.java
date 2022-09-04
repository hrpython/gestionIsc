package com.gestionIsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestionIsc.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/") //que je sois a / ou a /login, renvoie le formulaire login
	public String showlogin() {
		return "login";
	}

	@GetMapping("/login")
	public String viewlogin() {
		return "login";
	}

	@GetMapping("/user") //sur ma page user, je renvoie tout mes users enregistrés
	public String viewUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user";
	}
	@RequestMapping(value = "user/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteUser(@PathVariable Long id) {
		userService.deleteById(id);
		return "redirect:";
	}
}
