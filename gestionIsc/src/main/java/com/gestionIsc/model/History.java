package com.gestionIsc.model;

import java.time.LocalDate;

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
@ToString // va avec le print
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHistory;
	private String nom;
	private LocalDate date;
	private Long idUser;
	private String nameUser;
	private String message;


	
	public History(Long idHistory, String nom, LocalDate date, Long idUser, String nameUser, String message) {
		this.idHistory=idHistory;
		this.nom=nom;
		this.date=date;
		this.idUser=idUser;
		this.nameUser=nameUser;
		this.message=message;
	}
}
