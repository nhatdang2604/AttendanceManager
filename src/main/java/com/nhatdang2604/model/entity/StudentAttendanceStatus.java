package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

@Entity
@Table(name = "student_attendance_status")
public class StudentAttendanceStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7658272083900305023L;

	public enum ATTENDANCE_STATUS {
		Present, Absent
	}
	
	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(
			name = "student_id", 
			referencedColumnName = "id")
	private Student student;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumns({
		@PrimaryKeyJoinColumn(name = "schedule_id", referencedColumnName = "schedule_id"),
		@PrimaryKeyJoinColumn(name = "week_index", referencedColumnName = "week_index")})
	private SubjectWeek subjectWeek;
	
	@Column(name = "attendance_status")
	private String attendanceStatus;
	
	public StudentAttendanceStatus() {
		//do nothing
	}

	public StudentAttendanceStatus(Student student, SubjectWeek subjectWeek, String attendanceStatus) {
		this.student = student;
		this.subjectWeek = subjectWeek;
		this.attendanceStatus = attendanceStatus;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SubjectWeek getSubjectWeek() {
		return subjectWeek;
	}

	public void setSubjectWeek(SubjectWeek subjectWeek) {
		this.subjectWeek = subjectWeek;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	
	
	
}
