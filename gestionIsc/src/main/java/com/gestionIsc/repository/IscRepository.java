package com.gestionIsc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestionIsc.model.Isc;

@Repository
public interface IscRepository extends JpaRepository<Isc, Long> {

	/*
	 * @Transactional
	 * 
	 * @Modifying(clearAutomatically = true)
	 * 
	 * @Query(value =
	 * "select isc.id_isc, isc.id_isc from isc join isc on isc.id_isc=isc.id_isc on conflict (id_isc) do nothing"
	 * , nativeQuery = true) public void linkIdIscToIdIsc(Long idIsc,Long
	 * idIsc);
	 */
}
