package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_week")
public class SubjectWeek implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7602067339960852568L;

	//Attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(
			name = "schedule_id", 
			referencedColumnName = "id")
	private Schedule schedule;
	
	@Column(name = "week")
	private Integer weekIndex;	//From 1 - 15 weeks
	
	@Column(name = "date")
	private Date date;

	public SubjectWeek() {
		//do nothing
	}
	
	public SubjectWeek(Schedule schedule, Integer weekIndex, Date date) {
		this.schedule = schedule;
		this.weekIndex = weekIndex;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Integer getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(Integer weekIndex) {
		this.weekIndex = weekIndex;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
