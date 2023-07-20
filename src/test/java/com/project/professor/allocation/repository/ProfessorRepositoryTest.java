package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest { 

    @Autowired
    ProfessorRepository professorRepository;
    
    
    @Test
    void create() {
    	
    	Department dpt = new Department();
    	dpt.setId(2l);
    	
    	Professor prof = new Professor();
    	prof.setName("Teste2 Professor");
    	prof.setCpf("5145221");
    	prof.setDepartment(dpt);
    	
    	Professor prof2 = professorRepository.save(prof);
    	System.out.println(prof2);
    
    	
    }
    
    @Test
    void update() {
    	Professor prof = new Professor();
    	prof.setName("Psicologia Reversiva");
    	prof.setId(null);
    	
    	Professor prof2 = professorRepository.save(prof);
    	System.out.println(prof2);
    }

    @Test
    public void findAll() {
        List<Professor> professors = professorRepository.findAll();

        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
   
        Long id = 1L;

        Professor professor = professorRepository.findById(id).orElse(null);

        System.out.println(professor);
    }
    
    
//    @Test
//    public void findByDepartament() {
//    	Department dpt = new Department();
//    	List<Professor> dptList = professorRepository.findByDepartment(dpt);
//    	System.out.println(dptList);
//    }
    
     
    
    @Test
    public void deleteById() {
        Long id = 1L;

        professorRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        professorRepository.deleteAllInBatch();
    }
    
    
//  @Test
//  public void save_create() {
//      // Arrange
//      Professor professor = new Professor();
//      professor.setId(null);
//      professor.setName("Professor 1");
//      professor.setCpf("111.111.111-11");
//      professor.setDepartmentId(1L);
//
//      // Act
//      professor = professorRepository.save(professor);
//
//      // Print
//      System.out.println(professor);
//  }
}
