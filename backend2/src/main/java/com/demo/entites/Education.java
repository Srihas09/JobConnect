package com.demo.entites;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "education")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long educationId; // Unique ID for each education record
	@Column(name = "institute_name")
    private String institutionName; // Name of the educational institution
	@Column(name = "degree")
    private String degree; // Degree or certification (e.g., Bachelor's, Master's)
	@Column(name = "branch")
    private String branch; // Subject or field of study (e.g., Computer Science, History)
	@Column(name = "start_date")
    private Date startDate; // Date when the education started
	@Column(name = "end_date")
    private Date endDate; // Date when the education ended or will end (e.g., graduation date)
	@Column(name = "gpa")
    private double gradePointAverage; // GPA or grade percentage
	@Column(name = "description")
    private String description; // Brief description about the program/course
	@Column(name = "location")
    private String location; // The location of the institution (optional)
	@Column(name = "isCompleted")
    private boolean isCompleted; // Whether the program is completed or still ongoing

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobseeker_id")
	private Jobseeker jobseeker;

	public long getId() {
		return educationId;
	}

	public void setId(long id) {
		this.educationId = id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public double getGradePointAverage() {
		return gradePointAverage;
	}

	public void setGradePointAverage(double gradePointAverage) {
		this.gradePointAverage = gradePointAverage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Jobseeker getJobseeker() {
		return jobseeker;
	}

	public void setJobseeker(Jobseeker jobseeker) {
		this.jobseeker = jobseeker;
	}

	@Override
	public int hashCode() {
		return Objects.hash(branch, degree, description, endDate, gradePointAverage, educationId, institutionName, isCompleted,
				jobseeker, location, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Education other = (Education) obj;
		return Objects.equals(branch, other.branch) && Objects.equals(degree, other.degree)
				&& Objects.equals(description, other.description) && Objects.equals(endDate, other.endDate)
				&& Double.doubleToLongBits(gradePointAverage) == Double.doubleToLongBits(other.gradePointAverage)
				&& Objects.equals(educationId, other.educationId) && Objects.equals(institutionName, other.institutionName)
				&& isCompleted == other.isCompleted && Objects.equals(jobseeker, other.jobseeker)
				&& Objects.equals(location, other.location) && Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "Education [id=" + educationId + ", institutionName=" + institutionName + ", degree=" + degree + ", branch="
				+ branch + ", startDate=" + startDate + ", endDate=" + endDate + ", gradePointAverage="
				+ gradePointAverage + ", description=" + description + ", location=" + location + ", isCompleted="
				+ isCompleted + ", jobseeker=" + jobseeker + "]";
	}

	public Education() {
		
	}
	
	
	

}
