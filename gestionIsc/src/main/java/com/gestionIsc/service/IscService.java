package com.gestionIsc.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Isc;

public interface IscService {
	/*
	 * public void deleteById(Long id);
	 * public Optional<Address> findById(Long id);
	 */

	public Isc saveIsc(Isc isc);
	public List<Isc> findAll();
	public void deleteById(Long id);
	public Optional<Isc> findById(Long idIscs);
	public void genererMonEtiquette(Long idIsc, Long idProject) throws URISyntaxException, IOException, InvalidFormatException;

}
