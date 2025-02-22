package com.demo.entites;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "jobseeker")
public class Jobseeker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobseeker_id")
    private long jobSeekerId;
	@Column(name = "jobseeker_name")
    private String name;
	@Column(name = "jobseeker_email")
    private String email;
	@Column(name = "jobseeker_phone")
    private String phone;
	
    @Embedded
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> education;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experience;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
    
   
	public long getJobSeekerId() {
		return jobSeekerId;
	}

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setJobSeekerId(long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Education> getEducation() {
		return education;
	}


	public void setEducation(List<Education> education) {
		this.education = education;
	}


	public List<Experience> getExperience() {
		return experience;
	}


	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}


	public List<Application> getApplications() {
		return applications;
	}


	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	
	
	
	@Override
	public String toString() {
		return "Jobseeker [jobSeekerId=" + jobSeekerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", user=" + user + ", education=" + education + ", experience=" + experience
				+ ", applications=" + applications + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, applications, education, email, experience, jobSeekerId, name, phone, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jobseeker other = (Jobseeker) obj;
		return Objects.equals(address, other.address) && Objects.equals(applications, other.applications)
				&& Objects.equals(education, other.education) && Objects.equals(email, other.email)
				&& Objects.equals(experience, other.experience) && jobSeekerId == other.jobSeekerId
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(user, other.user);
	}


	public Jobseeker() {
		
	}
    
    

}
