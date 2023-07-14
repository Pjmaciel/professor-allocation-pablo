package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartamentRepositoryTest {

    @Autowired
    DepartamentRepository departmentRepository;
    
    @Test
    void findAll() {
    	List<Department> testeFindAll = departmentRepository.findAll();
    	
    	System.out.println(testeFindAll);
    }
    
    @Test
    void findById() {
    	Long id = 2l;
    	Optional<Department> testeFindByIDepartament = departmentRepository.findById(id);
    	Department dpt = testeFindByIDepartament.orElse(null); 
    	System.out.println(dpt);
    	
    }
    
    @Test
    void create() {
    	Department dpt1 = new Department();
    	dpt1.setName("Aula Bruno");
    	dpt1.setId(null);
    	
    	Department dpt2 = departmentRepository.save(dpt1);
    	System.out.println(dpt2);
    	
    }
    
    @Test
    void update() {
    	Department dpt1 = new Department();
    	dpt1.setName("Psicologia Reversiva");
    	dpt1.setId(4l);
    	
    	Department dpt2 = departmentRepository.save(dpt1);
    	System.out.println(dpt2);
    }
    @Test
    void deleteById() {
    	long id = 4l;
    	departmentRepository.deleteById(id);
    }
    
    @Test
    void deleteAll() {
    	departmentRepository.deleteAllInBatch();
    }
    
//    @Test
//    void save_create() {
//    	Department departament = new Departamente();
//    	ProfessorRepository professorRepository = new Professor();
//    }



}
