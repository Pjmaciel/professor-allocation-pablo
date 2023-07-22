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

	public void deleteById(Long id) {

		if (repo.existsById(id)) {

			repo.deleteById(id);

		}

	}

	public void deleteAll() {

		repo.deleteAllInBatch();

	}

	private Allocation saveInternal(Allocation allocation) {
		if (allocation.getHourStart().compareTo(allocation.getHourEndTime()) > 0) {
			throw new RuntimeException("A hora de início não pode ser depois da hora de término.");
		} else {
			Allocation savedAllocation = repo.save(allocation); // Salva a alocação no banco de dados

			Long professorId = savedAllocation.getProfessor().getId(); // Obtém o ID do professor associado à alocação
			Professor professor = profService.findById(professorId); // Obtém o professor com base no ID
			savedAllocation.setProfessor(professor); // Define o professor na alocação atualizada

			Long courseId = savedAllocation.getCourse().getId(); // Obtém o ID do curso associado à alocação
			Course course = courseService.findById(courseId); // Obtém o curso com base no ID
			savedAllocation.setCourse(course); // Define o curso na alocação atualizada

			return savedAllocation; // Retorna a alocação salva atualizada
		}
	}

}
