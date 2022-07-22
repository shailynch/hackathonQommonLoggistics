package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.DriverDAO;
import com.qa.ims.persistence.domain.Driver;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class DriverController implements CrudController<Driver> {

	public static final Logger LOGGER = LogManager.getLogger();

	private DriverDAO driverDAO;
	private Utils utils;

	public DriverController(DriverDAO driverDAO, Utils utils) {
		super();
		this.driverDAO = driverDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Driver> readAll() {
		List<Driver> drivers = driverDAO.readAll();
		for (Driver driver : drivers) {
			LOGGER.info(driver);
		}
		return drivers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Driver create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Driver driver = driverDAO.create(new Driver(firstName, surname));
		LOGGER.info("driver created");
		return driver;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Driver update() {
		LOGGER.info("Please enter the id of the Driver you would like to update");
		Long driverID = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Driver driver = driverDAO.update(new Driver(driverID, firstName, surname));
		LOGGER.info("Driver Updated");
		return driver;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long driverID = utils.getLong();
		return driverDAO.delete(driverID);
	}

}
