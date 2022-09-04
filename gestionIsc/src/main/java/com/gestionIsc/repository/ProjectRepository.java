package com.gestionIsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestionIsc.model.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query(value="delete from ProjectAddress pa where pa.idAdresse = ?1 and pa.idproject= ?2", nativeQuery = true)
	public void deleteByIdadresseByIdproject(Long idAdresse, Long idProject);
	
}
