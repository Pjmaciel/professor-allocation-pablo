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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DayOfWeek", nullable = false)
	private DayOfWeek dayOfWeek;

	@Column(name = "hourStart", nullable = false)
	private Time hourStart;

	@Column(name = "hourEndTime", nullable = false)
	private Time hourEndTime;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Professor professor;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Course course;
	
	
	

}
