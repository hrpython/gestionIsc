package com.gestionIsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionIsc.model.Address;
import com.gestionIsc.model.Be;
import com.gestionIsc.model.Isc;

@Repository
public interface BeRepository extends JpaRepository<Be, Long>{

}
