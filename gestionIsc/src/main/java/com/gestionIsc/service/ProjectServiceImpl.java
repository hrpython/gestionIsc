package com.gestionIsc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Isc;
import com.gestionIsc.model.Project;
import com.gestionIsc.repository.AddressRepository;
import com.gestionIsc.repository.IscRepository;
import com.gestionIsc.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	IscRepository iscRepository;
	/*equivalent des autowired
	 * private ProjectRepository projectRepository; private AddressRepository
	 * addressRepository; private IscRepository iscRepository;
	 * 
	 * public ProjectServiceImpl(ProjectRepository projectRepository,
	 * AddressRepository addressRepository) { super(); this.projectRepository =
	 * projectRepository; this.addressRepository = addressRepository; }
	 */

	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		projectRepository.deleteById(id);
	}

	public void updateProject(Project project) {
		projectRepository.save(project);
	}

	@Override
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}

	

	@Override
	public void retirerAdresseProjet(Long idAdresse, Long idProject) {
		Project project = null;
		Optional<Project> optional = projectRepository.findById(idProject);
		Optional<Address> optionala = addressRepository.findById(idAdresse);
		Address address = null;
		if(optional.isPresent() && optionala.isPresent()) {
			project=optional.get();
			address = optionala.get();
			project.getAddresses().remove(address);
			projectRepository.save(project);}
	}
		

	@Override
	public List<Address> findAddressFromProjectAddress(Long id) {
		Project project = null;
		Optional<Project> optional = projectRepository.findById(id);
		if(optional.isPresent()) {
			project=optional.get();
			return project.getAddresses();
		}
		return null ;
	}
	@Override
	public List<Isc> findIscFromProjectIsc(Long id) {
		Project project = null;
		Optional<Project> optional = projectRepository.findById(id);
		if(optional.isPresent()) {
			project=optional.get();
			return project.getIscs();
		}
		return null ;
	}

	@Override
	public void retirerIscProjet(Long idIsc, Long idProject) {
		Project project = null;
		Optional<Project> optional = projectRepository.findById(idProject);
		Optional<Isc> optionala = iscRepository.findById(idIsc);
		Isc isc = null;
		if(optional.isPresent() && optionala.isPresent()) {
			project=optional.get();
			isc = optionala.get();
			project.getIscs().remove(isc);
			projectRepository.save(project);}
	}

}
