package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocation Controller")// a nota a classe
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		this.allocationService = allocationService;
	}
	
	@Operation(summary = "Busca Alocação Pelo Id")
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE) // Aqui ja tem o meto
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {

		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {

			return new ResponseEntity<>(allocation, HttpStatus.OK);

		}

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<List<Allocation>>(allocations, HttpStatus.OK);

	}

	@ApiResponses({
		@ApiResponse(responseCode = "201",description = "Alocação criada Com Sucesso!"),
		@ApiResponse(responseCode = "400",description = "Horario de inicio deve ser menor do que o horario de fim",content = @Content),		
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(@RequestBody Allocation alloc) {

		try {

			Allocation allocation = allocationService.create(alloc);

			return new ResponseEntity<Allocation>(allocation, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Alocação Atualizada Com Sucesso!"),
		@ApiResponse(responseCode = "400",description = "Horario de inicio deve ser menor do que o horario de fim",content = @Content),
		@ApiResponse(responseCode = "404",description = "Não foi encontrada a Alocação",content = @Content),
		
	})
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id,
			@RequestBody Allocation allocation) {
		try {
			allocation.setId(id);
			allocation = allocationService.udpate(allocation);

			if (allocation == null) {
				return new ResponseEntity<Allocation>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Allocation>(allocation, HttpStatus.OK);
			}

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/{allocation_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
