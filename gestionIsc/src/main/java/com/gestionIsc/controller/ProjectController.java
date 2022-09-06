package com.gestionIsc.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Be;
import com.gestionIsc.model.History;
import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;
import com.gestionIsc.repository.UserRepository;
import com.gestionIsc.service.AddressService;
import com.gestionIsc.service.BeService;
import com.gestionIsc.service.HistoryService;
import com.gestionIsc.service.IscService;
import com.gestionIsc.service.ProjectService;
import com.gestionIsc.service.UserService;

import lombok.extern.slf4j.Slf4j;

//import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProjectController {
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	ProjectService projectService;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressService addressService;
	@Autowired
	IscService iscService;
	@Autowired
	BeService beService;
	@Autowired
	HistoryService historyService;

	@ModelAttribute("project")
	public Project project() {
		return new Project();
	}

	@PostMapping("/registrationProject")
	public String registerproject(@ModelAttribute("sentproject") Project project,
			@ModelAttribute("history") History history) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long idUser = userRepository.findByNameUser(auth.getName()).getIdUser();//////////////////////// ********************************************************************************
		String nameUser = userRepository.findByNameUser(auth.getName()).getNameUser();
		// logger.info("idUser" + idUser);
		LocalDate dateProjet = LocalDate.now();
		projectService.saveProject(new Project(project.getNameProject(), project.getBonCommande(), project.getLots(),
				idUser, project.getNumAppelationProtegee(), project.getNumPcs(), project.getIndicePcs(), dateProjet,
				null, null));
		String message = "Création du projet";
		historyService.saveHistory(
				new History(history.getIdHistory(), project.getNameProject(), dateProjet, idUser, nameUser, message));
		logger.info("idProject" + project.getIdProject());
		return "redirect:showProject";
	}

	@GetMapping("/registrationProject")
	public String registrationproject(Model model) {
		return "/registrationProject";
	}

	@GetMapping("/showProject")
	public String showAllProjects(Model model) {
		model.addAttribute("projects", projectService.findAll());
		return "/showProject";
	}

	@RequestMapping(value = "showProject/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteProject(@PathVariable Long id) {
		projectService.deleteById(id);
		return "redirect:";
	}

	@GetMapping("/updateProject/{id}")
	public String updateProject(@PathVariable Long id, Model model) {
		Project project = null;
		Optional<Project> optional = projectService.findById(id);
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("PAS DE PROJET POUR L'ID" + id);
		}
		model.addAttribute("projects", project);
		return "updateProject";
	}

	@PostMapping("/updateProject")
	public String updateMyProject(@ModelAttribute("project") Project project) {
		projectService.saveProject(project);
		return "redirect:showProject";
	}

//page affichage adresses et isc
	@GetMapping("/completeProject/{id}")
	public String completeMyProject(@PathVariable Long id, Model model, Model model1, Model model2, Model model3) {
		// logger.info("Démarrage affichage projet initial ");
		Project project = null;
		Optional<Project> optional = projectService.findById(id);
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("PAS DE PROJET POUR L'ID" + id);
		}
		// logger.info("affichage données projet " + project);
		model.addAttribute("projects", project);
		List<Address> addresses = new ArrayList<Address>();
		// logger.info("création addresses ");
		addresses = projectService.findAddressFromProjectAddress(id);
		// logger.info("retour adresse " + addresses);
		model1.addAttribute("addresses", addresses);

		model2.addAttribute("projects", project);
		List<Isc> iscs = new ArrayList<Isc>();
		iscs = projectService.findIscFromProjectIsc(id);
		model3.addAttribute("iscs", iscs);
		return "/completeProject";
	}

	@GetMapping("/retirerAdresseProjet/{idAdresse}/{idProject}")
	public String retirerAdresseProjet(@PathVariable Long idAdresse, @PathVariable Long idProject) {
		projectService.retirerAdresseProjet(idAdresse, idProject);
		return "redirect:/completeProject/{idProject}";
	}

//voir les addresse quon peut ajouter
	@GetMapping("/addTempAddressProject/{id}")
	public String addAddressToProject(@PathVariable Long id, Model model, Model model1) {
		logger.info("id reçu " + id);
		model.addAttribute("addresses", addressService.findAll());
		Optional<Project> optional = projectService.findById(id);
		Project project = null;
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("PAS DE PROJET POUR L'ID" + id);
		}
		model.addAttribute("projects", project);
		List<Address> addresses = new ArrayList<Address>();
		addresses = addressService.findAll();
		model1.addAttribute("addresses", addresses);
		return "/addAddressProject";
	}

	// ajouter l'adresse au projet

	@GetMapping("/addTempAddressProject/{id}/{idProject}")
	public String addAddressToProject(@PathVariable Long id, @PathVariable Long idProject) {
		logger.info("id reçu " + id + " mon id projet  " + idProject);
		Optional<Project> optional = projectService.findById(idProject);
		Optional<Address> optionala = addressService.findById(id);
		Project projet = null;
		Address address = null;
		if (optional.isPresent() && optionala.isPresent()) {
			projet = optional.get();
			address = optionala.get();
			projet.getAddresses().add(address);
			projectService.saveProject(projet);
			return "redirect:/completeProject/{idProject}";
		}
		return "redirect:showProject";
	}

	// voir les isc qu'on peut ajouter
	@GetMapping("/addTempIscProject/{id}")
	public String addIscToProject(@PathVariable Long id, Model model, Model model1) {
		model.addAttribute("iscs", iscService.findAll());
		Optional<Project> optional = projectService.findById(id);
		Project project = null;
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("PAS DE PROJET POUR L'ID" + id);
		}
		model.addAttribute("projects", project);
		List<Isc> iscs = new ArrayList<Isc>();
		iscs = iscService.findAll();
		model1.addAttribute("iscs", iscs);
		return "/addIscProject";
	}

	@GetMapping("/addTempIscProject/{id}/{idProject}")
	public String addIscToProject(@PathVariable Long id, @PathVariable Long idProject) {
		Optional<Project> optional = projectService.findById(idProject);
		Optional<Isc> optionala = iscService.findById(id);
		Project projet = null;
		Isc isc = null;
		if (optional.isPresent() && optionala.isPresent()) {
			projet = optional.get();
			isc = optionala.get();
			projet.getIscs().add(isc);
			projectService.saveProject(projet);
			return "redirect:/completeProject/{idProject}";
		}
		return "redirect:showProject";
	}

	@GetMapping("/retirerIscProjet/{idIsc}/{idProject}")
	public String retirerIscProjet(@PathVariable Long idIsc, @PathVariable Long idProject) {
		projectService.retirerIscProjet(idIsc, idProject);
		return "redirect:/completeProject/{idProject}";
	}

	@GetMapping("/genererBordereau/{idProject}")
	public String addressedubordereau(@PathVariable Long idProject, Model model, Model model1, Model model2
		, Model model3	) {
		Project project = null;
		Optional<Project> optional = projectService.findById(idProject);
		if (optional.isPresent()) {
			project = optional.get();
		} else {
			throw new RuntimeException("PAS DE PROJET POUR L'ID" + idProject);
		}
		model.addAttribute("projects", project);
		List<Address> addresses = new ArrayList<Address>();
		addresses = projectService.findAddressFromProjectAddress(idProject);
		model1.addAttribute("addresses", addresses);
		List<Isc> iscs = new ArrayList<Isc>();
		iscs = projectService.findIscFromProjectIsc(idProject);
		model2.addAttribute("iscs", iscs);
		model3.addAttribute("idProject", idProject);
		// logger.info("id de mon projet : " + idProject);
		return "genererBordereau";
	}

	@PostMapping("/genererBordereau")
	public String genererBordereau(
			@RequestParam(value = "project", required = false) Project project,
	        @RequestParam(value = "address", required = false) Address address,
	        @RequestParam(value = "iscs", required = false) List<Long> iscsId,
	        @RequestParam(value = "idPro", required = false)  Long idProject)
	//		 , @ModelAttribute("be") Be be)
	{
		Be be = new Be();
		be=beService.saveBe(be);
		be.setDateCreationBe(LocalDate.now());
		List<Isc> iscs = new ArrayList<Isc>();
		iscs = iscsId.stream().map(f -> iscService.findById(f).get()).collect(Collectors.toList());
		be.setIscs(iscs);
		be.setIdIscs(idProject);
		log.info(be.toString());
		beService.saveBe(be);
		
		Long idDestinataire = address.getIdAdresse();
		addressService.genererMonBordereau(idProject, idDestinataire, iscsId);
		log.info("id de mon projet : " + idProject + "id de mon adresse : " +idDestinataire );
		return "redirect:/completeProject/{idProject}";
	}

	@GetMapping("/genererEtiquette/{idIsc}/{idProject}")
	public String genererMonEtiquette(@PathVariable Long idIsc, @PathVariable Long idProject)
			throws InvalidFormatException, URISyntaxException, IOException {
		iscService.genererMonEtiquette(idIsc, idProject);
		return "redirect:/completeProject/{idProject}";
	}
	/*
	 * @PostMapping(value = "/saveBe") public String saveBe(@ModelAttribute
	 * ("address") Address address, Model model) { model.addAttribute("getdata",
	 * person.toString()); return "display"; }
	 */
	/*
	 * @GetMapping("/TempBe/{idProject}/{idAdresse}/{idIsc}") public String
	 * addIscToProject(@PathVariable Long idProject, @PathVariable Long idAdresse,
	 * 
	 * @PathVariable Long idIsc, Model model, Model model1, Model model2) {
	 * Optional<Isc> optionala = iscService.findById(idIsc); Optional<Address>
	 * optionalb = addressService.findById(idAdresse);
	 * 
	 * Be be = null; Isc isc = null; Address address = null;
	 * 
	 * if (optionala.isPresent() && optionalb.isPresent()) { isc = optionala.get();
	 * address = optionalb.get(); idAdresse = address.getIdAdresse();
	 * 
	 * be = beService.saveBe(new Be(be.getIdBordereau(), be.getDateCreationBe(),
	 * null, idAdresse)); return "redirect:/Be/{idBordereau}"; } return "/Be"; }
	 */

}
