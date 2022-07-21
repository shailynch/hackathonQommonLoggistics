package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Driver {
	
	private Long driverID;
	private String firstName;
	private String surname;


	public Driver(Long driverID, String firstName, String surname) {
		super();
		this.driverID = driverID;
		this.firstName = firstName;
		this.surname = surname;
	}

	public Driver( String firstName, String surname) {
		super();
		this.driverID = driverID;
		this.firstName = firstName;
		this.surname = surname;
	}

	public Long getDriverID() {
		return driverID;
	}


	public void setDriverID(Long driverID) {
		this.driverID = driverID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	@Override
	public int hashCode() {
		return Objects.hash(driverID, firstName, surname);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(driverID, other.driverID) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(surname, other.surname);
	}


	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", firstName=" + firstName + ", surname=" + surname + "]";
	}
	
	
}
