package com.gestionIsc.model;

import java.io.SequenceInputStream;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Isc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idIsc;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate DateIsc;
	private String typeSupport;
	private String classification;
	private int numExemplaire;
	private int numCopie;
	private Long idUser;


	public Isc(LocalDate DateIsc,String typeSupport,String classification,
			int numExemplaire, int numCopie, Long idUser) {
		this.DateIsc=DateIsc;
		this.typeSupport=typeSupport;
		this.classification=classification;
		this.numExemplaire=numExemplaire;
		this.numCopie=numCopie;
		this.idUser=idUser;
	}
}
