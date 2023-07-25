package com.project.professor.allocation.entity;

import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Allocation {

	@JsonProperty(access = Access.READ_ONLY) // serealização - pega o objeto java e transforma em JSON | desserealização- faz o inverso;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DayOfWeek", nullable = false)
	private DayOfWeek dayOfWeek;

	@Schema(example = "19:00:00", type = "string")
	@Column(name = "hourStart", nullable = false)
	private Time hourStart;

	@Schema(example = "22:00:00", type = "string")
	@Column(name = "hourEndTime", nullable = false)
	private Time hourEndTime;

	@Schema(allOf = Professor.class, accessMode = AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Professor professor;

	@Schema(allOf = Course.class, accessMode = AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Course course;

//	@JsonProperty(access = Access.WRITE_ONLY)
	public void setIdProfessor(Long professorId) {
		Professor professor = new Professor();
		professor.setId(professorId);
		this.setProfessor(professor);
	}
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	public void setIdCourse(Long courseId) {
		Course course = new Course();
		course.setId(courseId);
		this.setCourse(course);
	}
	

}
