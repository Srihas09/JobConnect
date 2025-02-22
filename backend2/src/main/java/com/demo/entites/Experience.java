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
@Table(name = "experience")
public class Experience {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long experienceId; // Unique ID for each experience record
	    @Column(name = "company_name")
	    private String companyName; // Name of the company or organization
	    @Column(name = "experience_years")
	    private int years; // Name of the company or organization
	    @Column(name = "job_title")
	    private String jobTitle; // Job title or position (e.g., Software Engineer)
	    @Column(name = "job_description")
	    private String jobDescription; // Description of the job role
	    @Column(name = "start_date")
	    private Date startDate; // Start date of the employment
	    @Column(name = "end_date")
	    private Date endDate; // End date of the employment (if applicable)
	    @Column(name = "iscurrently_employed")
	    private boolean isCurrentlyEmployed; // Whether the person is currently employed there
	    @Column(name = "location")
	    private String location; // The location of the job
	    @Column(name = "salary")
	    private double salary; // Salary for the job (optional)
	    @Column(name = "department")
	    private String department; // Department where the employee worked (optional)
	    
	    @JsonIgnore
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="jobseeker_id")
		private Jobseeker jobseeker;

		public long getExperienceId() {
			return experienceId;
		}

		public void setExperienceId(long experienceId) {
			this.experienceId = experienceId;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public int getYears() {
			return years;
		}

		public void setYears(int years) {
			this.years = years;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getJobDescription() {
			return jobDescription;
		}

		public void setJobDescription(String jobDescription) {
			this.jobDescription = jobDescription;
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

		public boolean isCurrentlyEmployed() {
			return isCurrentlyEmployed;
		}

		public void setCurrentlyEmployed(boolean isCurrentlyEmployed) {
			this.isCurrentlyEmployed = isCurrentlyEmployed;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public Jobseeker getJobseeker() {
			return jobseeker;
		}

		public void setJobseeker(Jobseeker jobseeker) {
			this.jobseeker = jobseeker;
		}

		@Override
		public int hashCode() {
			return Objects.hash(companyName, department, endDate, experienceId, isCurrentlyEmployed, jobDescription,
					jobTitle, jobseeker, location, salary, startDate, years);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Experience other = (Experience) obj;
			return Objects.equals(companyName, other.companyName) && Objects.equals(department, other.department)
					&& Objects.equals(endDate, other.endDate) && experienceId == other.experienceId
					&& isCurrentlyEmployed == other.isCurrentlyEmployed
					&& Objects.equals(jobDescription, other.jobDescription) && Objects.equals(jobTitle, other.jobTitle)
					&& Objects.equals(jobseeker, other.jobseeker) && Objects.equals(location, other.location)
					&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
					&& Objects.equals(startDate, other.startDate) && years == other.years;
		}

		@Override
		public String toString() {
			return "Experience [experienceId=" + experienceId + ", companyName=" + companyName + ", years=" + years
					+ ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", isCurrentlyEmployed=" + isCurrentlyEmployed + ", location=" + location
					+ ", salary=" + salary + ", department=" + department + ", jobseeker=" + jobseeker + "]";
		}

		public Experience() {
			super();
			// TODO Auto-generated constructor stub
		}

		

}
