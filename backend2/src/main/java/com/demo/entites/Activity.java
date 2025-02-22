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
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id")
    private long activityId;

    @Column(name = "activity_type")
    private String activityType; // e.g., "Applied for Job", "Updated Profile"
    
    @Column(name = "activity_date")
    private Date activityDate;
    
    @Column(name = "jobseeker_id")
    private long jobSeekerId;
    
    @Column(name = "jobpost_id")
    private long jobPostId;
    
    @Column(name = "employer_id")
    private long employerId;

    private String details; // Extra information if necessary (e.g., description of changes made)

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public long getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public long getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(long jobPostId) {
		this.jobPostId = jobPostId;
	}

	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activityDate, activityId, activityType, details, employerId, jobPostId, jobSeekerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return Objects.equals(activityDate, other.activityDate) && activityId == other.activityId
				&& Objects.equals(activityType, other.activityType) && Objects.equals(details, other.details)
				&& employerId == other.employerId && jobPostId == other.jobPostId && jobSeekerId == other.jobSeekerId;
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityType=" + activityType + ", activityDate="
				+ activityDate + ", jobSeekerId=" + jobSeekerId + ", jobPostId=" + jobPostId + ", employerId="
				+ employerId + ", details=" + details + "]";
	}

	
}
	