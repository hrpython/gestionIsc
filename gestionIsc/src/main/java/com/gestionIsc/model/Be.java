package com.gestionIsc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Be {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBordereau;
	private LocalDate DateCreationBe;
	private Long idIscs;

	@OneToMany
	public List<Isc> iscs = new ArrayList<>();

//	public Be(Long idBordereau, LocalDate DateCreationBe, Long idIscs) {
//		this.idBordereau = idBordereau;
//		this.DateCreationBe = DateCreationBe;
//		this.idIscs = idIscs;
//
//	}

}
