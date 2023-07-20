package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private AllocationRepository repo;
	private ProfessorService profService;
	private CourseService courseService;

	public AllocationService(AllocationRepository repo, ProfessorService profService, CourseService courseService) {
		this.repo = repo;
		this.profService = profService;
		this.courseService = courseService;

	}

	public Allocation findById(Long id) {

		Optional<Allocation> findById = repo.findById(id);
		Allocation allocation = findById.orElse(null);

		return allocation;

	}

	public List<Allocation> findAll() {

		List<Allocation> listaDeAllocations = repo.findAll();

		return listaDeAllocations;
	}

	public Allocation create(Allocation allocation) {

		allocation.setId(null);

		return saveInternal(allocation);

	}

	public Allocation udpate(Allocation allocation) {

		Long alloId = allocation.getId();

		
		if (repo.existsById(alloId)) {

			return saveInternal(allocation);

		} else {

			return null;

		}

	}

	private Allocation saveInternal(Allocation allocation) {
	    
		Allocation savedAllocation = repo.save(allocation); // Salva a alocação no banco de dados

	    Long professorId = savedAllocation.getProfessor().getId(); // Obtém o ID do professor associado à alocação

	    Professor professor = profService.findById(professorId); // Obtém o professor com base no ID

	    savedAllocation.setProfessor(professor); // Define o professor na alocação atualizada

	    return savedAllocation;
	}


}
