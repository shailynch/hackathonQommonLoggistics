package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Crate {

	private Long crateID;
	private Long scheduleID;
	private String area;

	public Long getCrateID() {
		return crateID;
	}

	public void setCrateID(Long crateID) {
		this.crateID = crateID;
	}

	public Long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(Long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		return Objects.hash(crateID, area);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crate other = (Crate) obj;
		return Objects.equals(crateID, other.crateID) && Objects.equals(area, other.area);
	}

	@Override
	public String toString() {
		return "Lorry [crateID=" + crateID + "Schedule ID" + scheduleID + ", area=" + area + "]";
	}

}
