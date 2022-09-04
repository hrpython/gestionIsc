package com.gestionIsc.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class Ref_be {
	private String numAppelationProtegee;
	private int numPcs; //string
	private String bonCommande;
	private String classification;
	private int numExemplaire;
	private int numCopie;
	private LocalDate dateIsc;
	private String nom;
	private String typeSupport;
	private Date DateCreationBe;
	private Long idBordereau;
	private String nameProject;
	private String organisme;


}
