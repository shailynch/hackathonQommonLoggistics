package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum EmployeeDomain {

	SCHEDULE(" Schedule information"), CRATE("crate information"), PRODUCT("product information"),
	STOP("To close the application");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private EmployeeDomain(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (EmployeeDomain domain : EmployeeDomain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static EmployeeDomain getDomain(Utils utils) {
		EmployeeDomain domain;
		while (true) {
			try {
				domain = EmployeeDomain.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
