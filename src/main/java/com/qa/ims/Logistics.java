package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrateController;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.DriverController;
import com.qa.ims.controller.LorryController;
import com.qa.ims.controller.ProductController;
import com.qa.ims.controller.ScheduleController;
import com.qa.ims.persistence.dao.CrateDAO;
import com.qa.ims.persistence.dao.DriverDAO;
import com.qa.ims.persistence.dao.LorryDAO;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.dao.ScheduleDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class Logistics {

	public static final Logger LOGGER = LogManager.getLogger();

	private final DriverController drivers;
	private final CrateController crates;
	private final ProductController products;
	private final LorryController lorrys;
	private final ScheduleController schedules;
	private final Utils utils;

	public Logistics() {
		this.utils = new Utils();
		final DriverDAO driverDAO = new DriverDAO();
		this.drivers = new DriverController(driverDAO, utils);
		final CrateDAO crateDAO = new CrateDAO();
		this.crates = new CrateController(crateDAO, utils);
		final LorryDAO lorryDAO = new LorryDAO();
		this.lorrys = new LorryController(lorryDAO, utils);
		final ProductDAO productDAO = new ProductDAO();
		this.products = new ProductController(productDAO, utils);
		final ScheduleDAO scheduleDAO = new ScheduleDAO();
		this.schedules = new ScheduleController(scheduleDAO, utils);

	}

	public void logisticSystem() {
		LOGGER.info("Welcome to the Logistics Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case DRIVER:
				active = this.drivers;
				break;
			case SCHEDULE:
				active = this.schedules;
				break;
			case CRATE:
				active = this.crates;
				break;
			case LORRY:
				active = this.lorrys;
				break;
			case PRODUCT:
				active = this.products;
				break;
			case STOP:
				return;
			default:
				break;
			}

			LOGGER.info(() -> "What would you like to do with " + domain.name().toLowerCase() + ":");

			Action.printActions();
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

}
