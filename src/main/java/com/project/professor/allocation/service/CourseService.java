package com.project.professor.allocation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {
	private CourseRepository repo;

	public CourseService(CourseRepository repo) {
		this.repo = repo;
	}

	public Course findById(Long id) {

		Optional<Course> findIdCourse = repo.findById(id);
		Course course = findIdCourse.orElse(null);

		return course;
	}

}
