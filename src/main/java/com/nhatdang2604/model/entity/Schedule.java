package com.nhatdang2604.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7686434131173320183L;

	public static final String[] WEEK_DAYS = {
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	};
	
	
	@Id
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Course course;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "schedule",
			orphanRemoval = true)
	private List<SubjectWeek> subjectWeeks;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "time")
	private LocalTime time;
	
	@Column(name = "week_day")
	private String weekDay;

	public Schedule() {
		//do nothing
	}
	
	
	public Schedule(Course course, List<SubjectWeek> subjectWeeks, LocalDate startDate, LocalDate endDate, LocalTime time,
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
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
		subjectWeeks.forEach((week)->{
			week.setSchedule(this);
		});
	}


	@Override
	public String toString() {
		return "Schedule [startDate=" + startDate
				+ ", endDate=" + endDate + ", time=" + time + ", weekDay=" + weekDay + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		return true;
	}

	
}
