package com.project.professor.allocation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.service.ProfessorService;

@RestController
public class ProfessorController {
	
	private ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	

}
