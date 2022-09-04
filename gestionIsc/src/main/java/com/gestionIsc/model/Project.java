package com.gestionIsc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproject")
	private Long idProject;
	private String nameProject;
	private String bonCommande;
	private int lots;
	private String numAppelationProtegee;
	private int numPcs;
	private String indicePcs;
	private Long idUser;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateProjet;

	@ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "Address_Project", joinColumns = @JoinColumn(name =
	// "idProject"), inverseJoinColumns = @JoinColumn(name = "idAdresse"))
	// permet de créer l'ensemble des adresses rattachées au projet
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Isc> iscs = new ArrayList<>();

	/*
	 * joindre isc à projet pour etablir la reference
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "projet_isc_join", joinColumns = { @JoinColumn(name =
	 * "idproject", referencedColumnName = "idproject") }, inverseJoinColumns =
	 * { @JoinColumn(name = "idisc", referencedColumnName = "id_isc") }) private Isc
	 * isc;
	 */

	public Project( String nameProject, String bonCommande, int lots, Long idUser, String numAppelationProtegee,
			int numPcs, String indicePcs, LocalDate dateProjet, List<Address> addresses, List<Isc> iscs) {
		this.nameProject = nameProject;
		this.bonCommande = bonCommande;
		this.lots = lots;
		this.idUser = idUser;
		this.numAppelationProtegee = numAppelationProtegee;
		this.numPcs = numPcs;
		this.indicePcs = indicePcs;
		this.dateProjet=dateProjet;
		this.addresses = addresses;
		this.iscs = iscs;
	}
}