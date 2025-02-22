package com.demo.entites;

import java.util.Date;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "jobpost")
public class Jobpost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobpost_id")
    private long jobPostId;
	@Column(name = "title")
    private String title;
	@Column(name = "description")
    private String description;
	@Column(name = "location")
    private String location;
	@Column(name = "salary")
    private double salary;
	@Column(name = "posted_date")
    private Date postedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id")
    private Employer employer;
    
    @JsonIgnore
    @OneToMany(mappedBy = "jobpost",cascade = CascadeType.ALL)
    private List<Application> applications;

	public long getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applications, description, employer, jobPostId, location, postedDate, salary, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jobpost other = (Jobpost) obj;
		return Objects.equals(applications, other.applications) && Objects.equals(description, other.description)
				&& Objects.equals(employer, other.employer) && jobPostId == other.jobPostId
				&& Objects.equals(location, other.location) && Objects.equals(postedDate, other.postedDate)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Jobpost [jobPostId=" + jobPostId + ", title=" + title + ", description=" + description + ", location="
				+ location + ", salary=" + salary + ", postedDate=" + postedDate + ", employer=" + employer
				+ ", applications=" + applications + "]";
	}
	
	

}
