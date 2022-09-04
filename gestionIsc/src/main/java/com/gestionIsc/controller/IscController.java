package com.gestionIsc.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;
import com.gestionIsc.repository.IscRepository;
import com.gestionIsc.repository.UserRepository;
import com.gestionIsc.service.IscService;
import com.gestionIsc.service.ProjectService;

@Controller
public class IscController {
	@Autowired
	IscService iscService;
	@Autowired
	ProjectService projectService;
	@Autowired
	IscRepository iscRepository;
	@Autowired
	UserRepository userRepository;

	@ModelAttribute("isc")
	public Isc isc() {
		return new Isc();
	}

	// enregistrer une nouvelle addresse
	@GetMapping("/registrationIsc")
	public String newisc() {
		return "/registrationIsc";
	}

	@PostMapping("/registrationIsc")
	public String registerIsc(@ModelAttribute("isc") Isc isc) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long idUser = userRepository.findByNameUser(auth.getName()).getIdUser();
		LocalDate DateIsc = LocalDate.now();
		Isc h=iscService.saveIsc(new Isc(isc.getIdIsc(), DateIsc,
				isc.getTypeSupport(), isc.getClassification(),
				isc.getNumExemplaire(), isc.getNumCopie(),idUser));
		Long idIsc=h.getIdIsc();
		//iscRepository.linkIdIscToIdIsc(idIsc, idIsc);
		return "redirect:showIsc";
	}

	// voir toutes mes addresses enregistrées sur ma page address

	@GetMapping("/showIsc")
	public String showAllIsc(Model model) {
		model.addAttribute("iscs", iscService.findAll());
		return "/showIsc";
	}

	// @DeleteMapping("address/{id}")

	@RequestMapping(value = "showIsc/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteIsc(@PathVariable Long id) {
		iscService.deleteById(id);
		return "redirect:";
	}


	/*
	 * @GetMapping("/addTempProjectToIsc/{id}") public String
	 * addProjectToIsc(@PathVariable Long id, Model model, Model model1) {
	 * model.addAttribute("project", projectService.findAll()); Optional<Project>
	 * optional = projectService.findById(id); Project project = null; if
	 * (optional.isPresent()) { project = optional.get(); } else { throw new
	 * RuntimeException("PAS DE PROJET POUR L'ID" + id); }
	 * model.addAttribute("projects", project); List<Project> projects = new
	 * ArrayList<Project>(); projects = projectService.findAll();
	 * model1.addAttribute("projects", projects); return "/addProjectToIsc"; }
	 */
	/*
	 * @GetMapping("/updateAddress/{id}") public String updateAddress(@PathVariable
	 * Long id, Model model) { Address address = null; Optional<Address> optional =
	 * addressService.findById(id); if (optional.isPresent()) { address =
	 * optional.get(); } else { throw new RuntimeException("PAS D'ADRESSE POUR L'ID"
	 * + id); } System.out.println("address found"); model.addAttribute("addresses",
	 * address); return "updateAddress"; }
	 */
	/*
	 * @PostMapping("/updateAddress") public String
	 * updateMyAddress(@ModelAttribute("address") Address address) {
	 * addressService.saveAddress(address); return "redirect:showAddress"; }
	 */
}