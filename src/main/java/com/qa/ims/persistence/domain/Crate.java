package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Crate {

	private Long crateId;
	private Long fkScheduleId;
	private String area;
	
	public Crate(Long crateId, Long fkScheduleId, String area) {
		super();
		this.crateId = crateId;
		this.fkScheduleId = fkScheduleId;
		this.area = area;
	}
	
	public Long getCrateId() {
		return crateId;
	}
	public void setCrateId(Long crateId) {
		this.crateId = crateId;
	}
	public Long getFkScheduleId() {
		return fkScheduleId;
	}
	public void setFkScheduleId(Long fkScheduleId) {
		this.fkScheduleId = fkScheduleId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(area, crateId, fkScheduleId);
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
		return Objects.equals(area, other.area) && Objects.equals(crateId, other.crateId)
				&& Objects.equals(fkScheduleId, other.fkScheduleId);
	}
	
	@Override
	public String toString() {
		return "Crate [crateId=" + crateId + ", fkScheduleId=" + fkScheduleId + ", area=" + area + "]";
	}
	
	
	
}
