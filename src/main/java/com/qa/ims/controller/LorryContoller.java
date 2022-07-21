package com.qa.ims.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.LorryDAO;
import com.qa.ims.persistence.domain.Lorry;
import com.qa.ims.utils.Utils;

public class LorryContoller implements CrudController<Lorry> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private LorryDAO lorryDAO;
	private Utils utils;

	public LorryController(LorryDAO lorryDAO, Utils utils) {
		super();
		this.lorryDAO = lorryDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Lorry> readAll() {
		List<Lorry> lorries = lorryDAO.readAll();
		for (Lorry lorry : lorries) {
			LOGGER.info(lorries);
		}
		return lorries;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Lorry create() {
		LOGGER.info("please enter the reg");
		String reg = utils.getString();
		Lorry lorry = lorryDAO.create(new Lorry(reg));
		LOGGER.info("Customer created");
		return lorry;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Lorry update() {
		LOGGER.info("Please enter the id of the Lorry you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the reg");
		String reg = utils.getString();
		Lorry lorry = lorryDAO.update(new Lorry(id, reg));
		LOGGER.info("Lorry Updated");
		return lorry;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return lorryDAO.delete(id);
	}

}
