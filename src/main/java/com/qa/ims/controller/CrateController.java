package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CrateDAO;
import com.qa.ims.persistence.domain.Crate;
import com.qa.ims.utils.Utils;

public class CrateController implements CrudController<Crate> {
	public static final Logger LOGGER = LogManager.getLogger();

	private CrateDAO crateDAO;
	private Utils utils;

	public CrateController(CrateDAO crateDAO, Utils utils) {
		super();
		this.crateDAO = crateDAO;
		this.utils = utils;
	}

	/**
	 * Reads all crates to the logger
	 */
	@Override
	public List<Crate> readAll() {
		List<Crate> crates = crateDAO.readAll();
		for (Crate crate : crates) {
			LOGGER.info(crate);
		}
		return crates;
	}

	/**
	 * Creates a crate by taking in user input
	 */
	@Override
	public Crate create() {
		LOGGER.info("Please enter a schedule id");
		Long scheduleID = utils.getLong();
		LOGGER.info("Please enter the crate area of operation");
		String area = utils.getString();
		Crate crate = crateDAO.create(new Crate(scheduleID, area));
		LOGGER.info("Crate created");
		return crate;
	}

	/**
	 * Updates an existing crate by taking in user input
	 */
	@Override
	public Crate update() {
		LOGGER.info("Please enter the id of the crate you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a schedule id");
		Long scheduleID = utils.getLong();
		LOGGER.info("Please enter the crate area of operation");
		String area = utils.getString();
		Crate crate = crateDAO.update(new Crate(id, scheduleID, area));
		LOGGER.info("Crate Updated");
		return crate;
	}

	/**
	 * Deletes an existing crate by the id of the crate
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the crate you would like to delete");
		Long id = utils.getLong();
		return crateDAO.delete(id);

	}
}
