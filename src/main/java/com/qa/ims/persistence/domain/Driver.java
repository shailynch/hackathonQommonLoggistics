package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Driver {

	private Long driverID;
	private String firstName;
	private String lastName;

	public Driver(Long driverID, String firstName, String lastName) {
		super();
		this.driverID = driverID;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(driverID, firstName, lastName);
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
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", firstName=" + firstName + ", surname=" + lastName + "]";
	}

}
