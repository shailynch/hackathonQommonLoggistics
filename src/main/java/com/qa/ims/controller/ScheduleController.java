package com.qa.ims.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ScheduleDAO;
import com.qa.ims.persistence.domain.Schedule;
import com.qa.ims.utils.Utils;

public class ScheduleController implements CrudController<Schedule> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ScheduleDAO scheduleDAO;
	private Utils utils;

	public ScheduleController(ScheduleDAO scheduleDAO, Utils utils) {
		super();
		this.scheduleDAO = scheduleDAO;
		this.utils = utils;
	}

	@Override
	public List<Schedule> readAll() {
		List<Schedule> schedules = scheduleDAO.readAll();
		for (Schedule schedule : schedules) {
			LOGGER.info(schedule);
		}
		return schedules;
	}

	@Override
	public Schedule create() {
		LOGGER.info("Please enter a Driver id");
		Long fkDriverID = utils.getLong();
		LOGGER.info("Please enter a Lorry id");
		Long fkLorryID = utils.getLong();
		LOGGER.info("Please enter an area");
		String area = utils.getString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		Schedule schedule = scheduleDAO.create(new Schedule(fkDriverID, fkLorryID, area, date));
		LOGGER.info("Schedule created");
		return schedule;
	}

	@Override
	public Schedule update() {
		LOGGER.info("Please enter the id of the schedule you would like to update");
		Long scheduleId = utils.getLong();
		LOGGER.info("Please enter a driver id");
		Long fkDriverID = utils.getLong();
		LOGGER.info("Please enter a lorry id");
		Long fkLorryID = utils.getLong();
		LOGGER.info("Please enter an area");
		String area = utils.getString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date = new Date(ts.getTime());
		Schedule schedule = scheduleDAO.update(new Schedule(scheduleId, fkDriverID, area, fkLorryID, date));
		LOGGER.info("Schedule Updated");
		return schedule;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the schedule you would like to delete");
		Long id = utils.getLong();
		return scheduleDAO.delete(id);
	}

}
