package model;

import java.time.LocalDate;

public class Viewer {
	
	private int viewerId;
	private String firstname;
	private String lastname;
	private LocalDate birthdate;
	private String gender;
	private String country;
	private String email;
	private String password;
	private String retriever;
	private Boolean isNewViewer;
	private int occupationId;
	
	
	public Occupation occupation;
	

	
	
	public Viewer() {
	}
	

	public Viewer(String firstname, String lastname, LocalDate birthdate, String gender, String country,
			String email, String password, String retriever, int occupationId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.gender = gender;
		this.country = country;
		this.email = email;
		this.password = password;
		this.retriever = retriever;
		this.occupationId = occupationId;
	}



	public int getViewerId() {
		return viewerId;
	}
	public void setViewerId(int viewerId) {
		this.viewerId = viewerId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetriever() {
		return retriever;
	}
	public void setRetriever(String retriever) {
		this.retriever = retriever;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}


   

	public int getOccupationId() {
		return occupationId;
	}


	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}


	@Override
	public String toString() {
		return "Viewer [viewerId=" + viewerId + ", firstname=" + firstname + ", lastname=" + lastname + ", birthdate="
				+ birthdate + ", gender=" + gender + ", country=" + country + ", email=" + email + ", password="
				+ password + ", retriever=" + retriever + ", isNewViewer=" + isNewViewer + ", occupationId="
				+ occupationId + ", occupation=" + occupation + "]";
	}



	
	

}
