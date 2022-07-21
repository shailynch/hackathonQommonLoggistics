package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Lorry {
	
	private Long lorryID;
	private String reg;
	
	
	public Lorry(Long lorryID, String reg) {
		this.lorryID = lorryID; 
		this.reg = reg; 
	}
	public Long getLorryID() {
		return lorryID;
	}
	public void setLorryID(Long lorryID) {
		this.lorryID = lorryID;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	@Override
	public int hashCode() {
		return Objects.hash(lorryID, reg);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lorry other = (Lorry) obj;
		return Objects.equals(lorryID, other.lorryID) && Objects.equals(reg, other.reg);
	}
	@Override
	public String toString() {
		return "Lorry [lorryID=" + lorryID + ", reg=" + reg + "]";
	}

	
	
}
