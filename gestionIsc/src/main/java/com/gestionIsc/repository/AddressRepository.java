package com.gestionIsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionIsc.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
}
