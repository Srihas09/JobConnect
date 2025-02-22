package com.demo.entites;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "application")
public class Application {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long applicationId;

	 private String status;// Applied, Shortlisted, Interviewed, etc.
	 
	 private Date appliedDate;

	 @ManyToOne(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "jobseeker_id")
	 private Jobseeker jobSeeker;

	 
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "jobpost_id")
	 private Jobpost jobpost;

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Jobseeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(Jobseeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Jobpost getJobPost() {
		return jobpost;
	}

	public void setJobPost(Jobpost jobPost) {
		this.jobpost = jobPost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationId, appliedDate, jobpost, jobSeeker, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		return Objects.equals(applicationId, other.applicationId) && Objects.equals(appliedDate, other.appliedDate)
				&& Objects.equals(jobpost, other.jobpost) && Objects.equals(jobSeeker, other.jobSeeker)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", status=" + status + ", appliedDate=" + appliedDate
				+ ", jobSeeker=" + jobSeeker + ", jobPost=" + jobpost + "]";
	}

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 


}
