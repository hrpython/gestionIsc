package com.gestionIsc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestionIsc.model.Address;
import com.gestionIsc.service.AddressService;
import com.gestionIsc.service.ProjectService;

@Controller
public class AddressController {
	@Autowired
	AddressService addressService;
	@Autowired
	ProjectService projectService;

	// voir toutes mes addresses enregistrées sur ma page address
	@GetMapping("/showAddress")
	public String showAllAddresses(Model model) {
		model.addAttribute("addresses", addressService.findAll());
		return "/showAddress";
	}

	// @DeleteMapping("address/{id}")
	@RequestMapping(value = "showAddress/{id}", method = {RequestMethod.DELETE, RequestMethod.GET })
	public String deleteAddress(@PathVariable Long id) {
		addressService.deleteById(id);
		return "redirect:";
	}

	@GetMapping("/updateAddress/{id}")
	public String updateAddress(@PathVariable Long id, Model model) {
		Address address = null;
		Optional<Address> optional = addressService.findById(id);
		if (optional.isPresent()) {
			address = optional.get();
		} else {
			throw new RuntimeException("PAS D'ADRESSE POUR L'ID" + id);
		}
		System.out.println("address found");
		model.addAttribute("addresses", address);
		return "updateAddress";
	}

	@PostMapping("/updateAddress")
	public String updateMyAddress(@ModelAttribute("address") Address address) {
		addressService.saveAddress(address);
		return "redirect:showAddress";
	}
}