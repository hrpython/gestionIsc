package com.gestionIsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionIsc.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByNameUser(String username);

}
