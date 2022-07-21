package com.qa.ims.persistence.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

	private Long productID;
	private Long crateID;
	private String address;
	private String status;
	private LocalDateTime lastUpdated;

	public Product(Long productID, Long crateID, String address, String status) {
		
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Long getCrateID() {
		return crateID;
	}

	public void setCrateID(Long crateID) {
		this.crateID = crateID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productID, address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productID, other.productID) && Objects.equals(address, other.address);
	}

	@Override
	public String toString() {
		return "Lorry [productID=" + productID + "crate ID" + crateID + ", address=" + address + "last updated"
				+ lastUpdated + "status" + status + "]";
	}

}
