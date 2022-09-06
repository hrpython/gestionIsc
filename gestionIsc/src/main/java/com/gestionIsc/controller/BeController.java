package com.gestionIsc.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Project;
import com.gestionIsc.repository.UserRepository;
import com.gestionIsc.service.AddressService;
import com.gestionIsc.service.IscService;
import com.gestionIsc.service.ProjectService;
import com.gestionIsc.service.UserService;

@Controller
public class BeController {
	private static Logger logger = LoggerFactory.getLogger(BeController.class);
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



	// page affichage adresses et projets

	
	/*
	 * @GetMapping("/completeProject/{id}") public String
	 * completeMyProject(@PathVariable Long id, Model model, Model model1) { Project
	 * project = null; model.addAttribute("projects", project); List<Address>
	 * addresses = new ArrayList<Address>();
	 * projectService.findAddressFromProjectAddress(id);
	 * model1.addAttribute("addresses", addresses); return "/completeProject"; }
	 */
	 

	/*
	 * @GetMapping("/Be") public String showAllProjects(Model model) {
	 * model.addAttribute("projects", projectService.findAll()); return
	 * "/showProject"; }
	 */

	/*
	 * //page affichage adresses et projets
	 * 
	 * @GetMapping("/completeProject/{id}") public String
	 * completeMyProject(@PathVariable Long id, Model model, Model model1, Model
	 * model2, Model model3) { //
	 * logger.info("Démarrage affichage projet initial "); Project project = null;
	 * Optional<Project> optional = projectService.findById(id); if
	 * (optional.isPresent()) { project = optional.get(); } else { throw new
	 * RuntimeException("PAS DE PROJET POUR L'ID" + id); } //
	 * logger.info("affichage données projet " + project);
	 * model.addAttribute("projects", project); List<Address> addresses = new
	 * ArrayList<Address>(); // logger.info("création addresses "); addresses =
	 * projectService.findAddressFromProjectAddress(id); //
	 * logger.info("retour adresse " + addresses); model1.addAttribute("addresses",
	 * addresses);
	 * 
	 * model2.addAttribute("projects", project); List<Isc> iscs = new
	 * ArrayList<Isc>(); iscs =
	 * projectService.findIscFromProjectIsc(id);
	 * model3.addAttribute("iscs", iscs); return "/completeProject"; }
	 */

	/*
	 * @PostMapping("/registrationProject") public String
	 * registerproject(@ModelAttribute("sentproject") Project project) {
	 * org.springframework.security.core.Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); Long idUser =
	 * userRepository.findByNameUser(auth.getName()).getIdUser(); //
	 * logger.info("idUser" + idUser); projectService.saveProject(new
	 * Project(project.getNameProject(), project.getBonCommande(),
	 * project.getLots(), idUser, project.getNumAppelationProtegee(),
	 * project.getNumPcs(), project.getIndicePcs(), null, null)); return
	 * "redirect:showProject"; }
	 */

	/*
	 * @GetMapping("/registrationProject") public String registrationproject(Model
	 * model) { return "/registrationProject"; }
	 */
	/*
	 * @GetMapping("/showProject") public String showAllProjects(Model model) {
	 * model.addAttribute("projects", projectService.findAll()); return
	 * "/showProject"; }
	 */

	/*
	 * @RequestMapping(value = "showProject/{id}", method = { RequestMethod.DELETE,
	 * RequestMethod.GET }) public String deleteProject(@PathVariable Long id) {
	 * projectService.deleteById(id); return "redirect:"; }
	 */

	/*
	 * @GetMapping("/updateProject/{id}") public String updateProject(@PathVariable
	 * Long id, Model model) { Project project = null; Optional<Project> optional =
	 * projectService.findById(id); if (optional.isPresent()) { project =
	 * optional.get(); } else { throw new RuntimeException("PAS DE PROJET POUR L'ID"
	 * + id); } model.addAttribute("projects", project); return "updateProject"; }
	 */
	/*
	 * @PostMapping("/updateProject") public String
	 * updateMyProject(@ModelAttribute("project") Project project) {
	 * projectService.saveProject(project); return "redirect:showProject"; }
	 */

	/*
	 * //page affichage adresses et projets
	 * 
	 * @GetMapping("/completeProject/{id}") public String
	 * completeMyProject(@PathVariable Long id, Model model, Model model1, Model
	 * model2, Model model3) { //
	 * logger.info("Démarrage affichage projet initial "); Project project = null;
	 * Optional<Project> optional = projectService.findById(id); if
	 * (optional.isPresent()) { project = optional.get(); } else { throw new
	 * RuntimeException("PAS DE PROJET POUR L'ID" + id); } //
	 * logger.info("affichage données projet " + project);
	 * model.addAttribute("projects", project); List<Address> addresses = new
	 * ArrayList<Address>(); // logger.info("création addresses "); addresses =
	 * projectService.findAddressFromProjectAddress(id); //
	 * logger.info("retour adresse " + addresses); model1.addAttribute("addresses",
	 * addresses);
	 * 
	 * model2.addAttribute("projects", project); List<Isc> iscs = new
	 * ArrayList<Isc>(); iscs =
	 * projectService.findIscFromProjectIsc(id);
	 * model3.addAttribute("iscs", iscs); return "/completeProject"; }
	 */
	/*
	 * @GetMapping("/retirerAdresseProjet/{idAdresse}/{idProject}") public String
	 * retirerAdresseProjet(@PathVariable Long idAdresse, @PathVariable Long
	 * idProject) { projectService.retirerAdresseProjet(idAdresse, idProject);
	 * return "redirect:/completeProject/{idProject}"; }
	 */

	/*
	 * //voir les addresse quon peut ajouter
	 * 
	 * @GetMapping("/addTempAddressProject/{id}") public String
	 * addAddressToProject(@PathVariable Long id, Model model, Model model1) {
	 * logger.info("id reçu " + id); model.addAttribute("addresses",
	 * addressService.findAll()); Optional<Project> optional =
	 * projectService.findById(id); Project project = null; if
	 * (optional.isPresent()) { project = optional.get(); } else { throw new
	 * RuntimeException("PAS DE PROJET POUR L'ID" + id); }
	 * model.addAttribute("projects", project); List<Address> addresses = new
	 * ArrayList<Address>(); addresses = addressService.findAll();
	 * model1.addAttribute("addresses", addresses); return "/addAddressProject"; }
	 */

	/*
	 * // ajouter l'adresse au projet
	 * 
	 * @GetMapping("/addTempAddressProject/{id}/{idProject}") public String
	 * addAddressToProject(@PathVariable Long id, @PathVariable Long idProject) {
	 * logger.info("id reçu " + id + " mon id projet  " + idProject);
	 * Optional<Project> optional = projectService.findById(idProject);
	 * Optional<Address> optionala = addressService.findById(id); Project projet =
	 * null; Address address = null; if (optional.isPresent() &&
	 * optionala.isPresent()) { projet = optional.get(); address = optionala.get();
	 * projet.getAddresses().add(address); projectService.saveProject(projet);
	 * return "redirect:/completeProject/{idProject}"; } return
	 * "redirect:showProject"; }
	 */

	/*
	 * // voir les isc qu'on peut ajouter
	 * 
	 * @GetMapping("/addTempIscProject/{id}") public String
	 * addIscToProject(@PathVariable Long id, Model model, Model model1) {
	 * model.addAttribute("iscs", iscService.findAll()); Optional<Project>
	 * optional = projectService.findById(id); Project project = null; if
	 * (optional.isPresent()) { project = optional.get(); } else { throw new
	 * RuntimeException("PAS DE PROJET POUR L'ID" + id); }
	 * model.addAttribute("projects", project); List<Isc> iscs = new
	 * ArrayList<Isc>(); iscs = iscService.findAll();
	 * model1.addAttribute("iscs", iscs); return "/addIscProject"; }
	 */
	/*
	 * @GetMapping("/addTempIscProject/{id}/{idProject}") public String
	 * addIscToProject(@PathVariable Long id, @PathVariable Long idProject) {
	 * logger.info("id reçu " + id + " mon id projet  " + idProject);
	 * Optional<Project> optional = projectService.findById(idProject);
	 * Optional<Isc> optionala = iscService.findById(id); Project projet =
	 * null; Isc isc = null; if (optional.isPresent() &&
	 * optionala.isPresent()) { projet = optional.get(); isc = optionala.get();
	 * projet.getIscs().add(isc); projectService.saveProject(projet);
	 * return "redirect:/completeProject/{idProject}"; } return
	 * "redirect:showProject"; }
	 */

	/*
	 * @GetMapping("/retirerIscProjet/{idIsc}/{idProject}") public String
	 * retirerIscProjet(@PathVariable Long idIsc, @PathVariable Long idProject)
	 * { projectService.retirerIscProjet(idIsc, idProject); return
	 * "redirect:/completeProject/{idProject}"; }
	 */
}
