package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public class Employee {
	private Utils utils = new Utils();
	public static final Logger LOGGER = LogManager.getLogger();
	String employeeType = null;

	public void checkEmployee() {

		do {
			LOGGER.info("Are you an employee or manager? Enter manager, employee or exit");
			employeeType = utils.getString();
			if (employeeType.contains("manager")) {
				Logistics logistics = new Logistics();
				logistics.logisticSystem();
			} else if (employeeType.contains("employee")) {
				Logistics logistics = new Logistics();
				logistics.employeeLogisticSystem();
			}

		} while (!employeeType.contains("exit"));

	}

}
