package com.gestionIsc.service;

import java.util.List;
import java.util.Optional;

import com.gestionIsc.model.Address;

public interface AddressService {
	public List<Address> findAll();
	public Address saveAddress(Address address);
	public void deleteById(Long id);
	public Optional<Address> findById(Long id);
	public void genererMonBordereau(Long idProject, Long idDestinataire, Long[] idIscs);
}
