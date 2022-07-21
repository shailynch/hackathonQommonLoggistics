package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.Objects;

public class Products {
	
	private Long productId;
	private String address;
	private Long crateID;
	private String status;
	private Date lastUpdates;
	

	
	public Products(Long productId, String address, Long crateID, String status, Date lastUpdates) {
		super();
		this.productId = productId;
		this.address = address;
		this.crateID = crateID;
		this.status = status;
		this.lastUpdates = lastUpdates;
	}
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCrateID() {
		return crateID;
	}
	public void setCrateID(Long crateID) {
		this.crateID = crateID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastUpdates() {
		return lastUpdates;
	}
	public void setLastUpdates(Date lastUpdates) {
		this.lastUpdates = lastUpdates;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", address=" + address + ", crateID=" + crateID + ", status="
				+ status + ", lastUpdates=" + lastUpdates + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, crateID, lastUpdates, productId, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(address, other.address) && Objects.equals(crateID, other.crateID)
				&& Objects.equals(lastUpdates, other.lastUpdates) && Objects.equals(productId, other.productId)
				&& Objects.equals(status, other.status);
	}
	
	

}
