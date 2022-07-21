package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Schedule;
import com.qa.ims.utils.DBUtils;

public class ScheduleDAO implements Dao<Schedule> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Schedule modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long fkScheduleID = resultSet.getLong("fkScheduleID");
		Date date = resultSet.getDate("date");
		Long fkLorryID = resultSet.getLong("fkLorryID");
		String area = resultSet.getString("area");
		Long scheduleId = resultSet.getLong("scheduleId");
		return new Schedule(fkScheduleID, date, fkLorryID, area, scheduleId);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Schedule> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Schedule");) {
			List<Schedule> schedule = new ArrayList<>();
			while (resultSet.next()) {
				schedule.add(modelFromResultSet(resultSet));
			}
			return schedule;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Schedule readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Schedule ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Schedule create(Schedule schedule) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)");) {
			statement.setLong(1, schedule.getFkScheduleID());
			statement.setDate(2, schedule.getDate());
			statement.setLong(2, schedule.getFkLorryID());
			statement.setString(2, schedule.getArea());
			statement.setLong(2, schedule.getScheduleId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Schedule read(Long scheduleID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Schedule WHERE scheduleID = ?");) {
			statement.setLong(1, scheduleID);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a Schedule in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Schedule update(Schedule Schedule) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Schedule SET firstname = ?, surname = ? WHERE scheduleID = ?");) {
			statement.setString(1, schedule.getFirstName());
			statement.setString(2, schedule.getSurname());
			statement.setLong(3, schedule.getScheduleID());
			statement.executeUpdate();
			return read(schedule.getScheduleID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a schedule in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long scheduleID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM Schedule WHERE scheduleID = ?");) {
			statement.setLong(1, scheduleID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
