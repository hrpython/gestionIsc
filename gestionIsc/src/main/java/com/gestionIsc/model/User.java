	package com.gestionIsc.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "user", schema = "public") //une fois après plus besoin
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String nameUser;
	private String passwordUser;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "idRole"))
	private Collection<Role> roles;

	public User(String name, String password, Collection<Role> roles) {
		this.nameUser = name;
		this.passwordUser = password;
		this.idUser = 100L;
		this.roles = roles;
	}

}
