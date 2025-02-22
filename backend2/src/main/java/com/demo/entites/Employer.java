package com.demo.entites;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "employer")
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employer_id")
    private long employerId;
	@Column(name = "employer_name")
	private String employerName;
	@Column(name = "company_name")
    private String companyName;
	@Column(name = "email")
    private String email;
	@Column(name = "phone")
    private String phone;
	
	@Embedded
    private Address address;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
	
	@JsonIgnore
    @OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
    private List<Jobpost> jobPosts;

	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Jobpost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<Jobpost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, companyName, email, employerId, employerName, jobPosts, phone, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employer other = (Employer) obj;
		return Objects.equals(address, other.address) && Objects.equals(companyName, other.companyName)
				&& Objects.equals(email, other.email) && employerId == other.employerId
				&& Objects.equals(employerName, other.employerName) && Objects.equals(jobPosts, other.jobPosts)
				&& Objects.equals(phone, other.phone) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", employerName=" + employerName + ", companyName=" + companyName
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", user=" + user + ", jobPosts="
				+ jobPosts + "]";
	}

	public Employer() {
		
	}

	
}
