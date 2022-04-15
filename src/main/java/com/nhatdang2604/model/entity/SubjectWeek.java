package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
	@ManyToOne(
			cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(
			name = "schedule_id", 
			referencedColumnName = "id")
	private Schedule schedule;
	
	@Id
	@Column(name = "week_index")
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
