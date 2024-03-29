package com.nhatdang2604.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student_attendance_status")
public class StudentAttendanceStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7658272083900305023L;

	public static final int NONE_STATUS_INDEX = 0;
	public static final int ABSENT_STATUS_INDEX = 1;
	public static final int PRESENT_STATUS_INDEX = 2;
	public static final String[] ATTENDANCE_STATUS = {
			"", "Vắng", "Có mặt"
	};
	
//	@Id
//	@Column(name = "student_id")
//	private Integer studentId;
//	
//	@Id
//	@Column(name = "schedule_id")
//	private Integer scheduleId;
//	
//	@Id
//	@Column(name = "week_index")
//	private Integer weekIndex;
	
	@Id
	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinColumn(
			name = "student_id", 
			referencedColumnName = "id")
	private Student student;
	
	@Id
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id"),
		@JoinColumn(name = "week_index", referencedColumnName = "week_index")})
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

	@Override
	public String toString() {
		return "StudentAttendanceStatus [student=" + student + ", subjectWeek=" + subjectWeek + ", attendanceStatus="
				+ attendanceStatus + "]";
	}
	
}
