package com.gestionIsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString //va avec le print
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAdresse;
	private String prenom;
	private String nom;
	private String genre;
	private String numNomRue;
	private String ville;
	private String codePostal;
	private String organisme;
	// constructeur renvoie tout sauf mon id
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name = "Address_Project", joinColumns = @JoinColumn(name =
	 * "idAdresse"), inverseJoinColumns = @JoinColumn(name = "idProject"))
	 */

	public Address(String nom, String prenom, String genre, String numNomRue, String ville, String codePostal, String organisme) {
		this.nom=nom;
		this.prenom=prenom;
		this.genre=genre;
		this.numNomRue=numNomRue;
		this.ville=ville;
		this.codePostal=codePostal;
		this.organisme=organisme;
	}
}
