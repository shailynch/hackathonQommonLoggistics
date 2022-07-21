package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.Objects;

public class Schedule {
	
	

	private Long fkDriverID;
	private Date date;
	private Long fkLorryID;
	private String area;
	private Long scheduleId;
	

	
	public Schedule(Long fkDriverID, Date date, Long fkLorryID, String area, Long scheduleId) {
		super();
		this.fkDriverID = fkDriverID;
		this.date = date;
		this.fkLorryID = fkLorryID;
		this.area = area;
		this.scheduleId = scheduleId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getFkDriverID() {
		return fkDriverID;
	}
	public void setFkDriverID(Long fkDriverID) {
		this.fkDriverID = fkDriverID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getFkLorryID() {
		return fkLorryID;
	}
	public void setFkLorryID(Long fkLorryID) {
		this.fkLorryID = fkLorryID;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
		
	@Override
	public int hashCode() {
		return Objects.hash(area, date, fkDriverID, fkLorryID, scheduleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(area, other.area) && Objects.equals(date, other.date)
				&& Objects.equals(fkDriverID, other.fkDriverID) && Objects.equals(fkLorryID, other.fkLorryID)
				&& Objects.equals(scheduleId, other.scheduleId);
	}

}
