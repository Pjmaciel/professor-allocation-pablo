package com.project.professor.allocation.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false, length = 11)
	private String cpf;
	
	
	@JoinColumn(nullable = false) //anotacoes apresentam metainformacoes aqui da nome para coluna
	@ManyToOne(optional = false)
	private Department department;// aqui nao é uma fk e sim um objeto


}
