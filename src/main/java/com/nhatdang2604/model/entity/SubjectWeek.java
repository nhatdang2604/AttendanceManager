package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject_week")
public class SubjectWeek implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7602067339960852568L;

	public static final int NUMBER_OF_WEEKS_PER_COURSE = 15;
	
	//Attributes
	@Id
	@ManyToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
	
	@Id
	@Column(name = "week_index")
	private Integer weekIndex;	//From 1 - 15 weeks
	
	@Column(name = "date")
	private LocalDate date;

	public SubjectWeek() {
		//do nothing
	}
	
	public SubjectWeek(Schedule schedule, Integer weekIndex, LocalDate date) {
		this.schedule = schedule;
		this.weekIndex = weekIndex;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SubjectWeek [scheduleId=" + schedule.getCourse().getId() + ", weekIndex=" + weekIndex + ", date=" + date + "]";
	}
	
}
