package com.gestionIsc.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gestionIsc.model.User;


public interface UserService extends UserDetailsService  {
	public List<User> findAll();
	public User saveUser(User user);
	public void deleteById(Long id);
}
