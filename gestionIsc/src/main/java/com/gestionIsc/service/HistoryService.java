package com.gestionIsc.service;

import java.util.List;
import java.util.Optional;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.History;

public interface HistoryService {
	public List<History> findAll();
	public History saveHistory(History history);
	/*
	 * public Address saveAddress(Address address); public void deleteById(Long id);
	 * public Optional<Address> findById(Long id); public void
	 * genererMonBordereau(Long idProject, Long idDestinataire, Long[] idIscs);
	 */
}
