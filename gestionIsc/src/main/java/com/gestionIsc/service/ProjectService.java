package com.gestionIsc.service;

import java.util.List;
import java.util.Optional;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;

public interface ProjectService {
	public List<Project> findAll();
	public Project saveProject(Project project);
	public void deleteById(Long id);
	public Optional<Project> findById(Long id);
	public List<Address> findAddressFromProjectAddress(Long id);
	public void retirerAdresseProjet(Long idAdresse, Long idProject);
	public List<Isc> findIscFromProjectIsc(Long id);
	public void retirerIscProjet(Long idIsc, Long idProject);
}
