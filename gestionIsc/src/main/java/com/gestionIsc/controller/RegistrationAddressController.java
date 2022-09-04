package com.gestionIsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gestionIsc.model.Address;
import com.gestionIsc.service.AddressService;

@Controller
public class RegistrationAddressController {
@Autowired
AddressService addressService;

@ModelAttribute("address")
public Address address() {
	return new Address();
}
//enregistrer une nouvelle addresse
@PostMapping("/registrationAddress")
public String registerAddress(@ModelAttribute("address") Address address) {
	addressService.saveAddress(address);
	return "redirect:showAddress"; //redirect : renvoie le formulaire et les données
}
//voir ma page d'enregistrement
@GetMapping("/registrationAddress")
public String newaddress() {
	return "/registrationAddress";
}
}
