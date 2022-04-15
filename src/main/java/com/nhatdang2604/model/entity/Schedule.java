package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7686434131173320183L;

	public enum WEEK_DAY {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}
	
	@OneToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(
			name = "id", 
			referencedColumnName = "id")
	private Course course;
	
	@OneToMany(mappedBy = "schedule_id")
	private List<SubjectWeek> subjectWeeks;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "time")
	private Time time;
	
	@Column(name = "week_day")
	private String weekDay;

	public Schedule() {
		//do nothing
	}
	
	
	public Schedule(Course course, List<SubjectWeek> subjectWeeks, Date startDate, Date endDate, Time time,
			String weekDay) {
		this.course = course;
		this.subjectWeeks = subjectWeeks;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.weekDay = weekDay;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public List<SubjectWeek> getSubjectWeeks() {
		return subjectWeeks;
	}

	public void setSubjectWeeks(List<SubjectWeek> subjectWeeks) {
		this.subjectWeeks = subjectWeeks;
	}
	
	
	
}
