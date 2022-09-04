package com.gestionIsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionIsc.model.Be;
import com.gestionIsc.repository.BeRepository;

@Service
public class BeServiceImpl implements BeService {
	@Autowired
	BeRepository beRepository;


	public Be saveBe(Be be) {
		return beRepository.save(be);
	}
	
}
