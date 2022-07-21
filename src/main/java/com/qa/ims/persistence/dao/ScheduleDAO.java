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
		Date date = resultSet.getDate("date");
		Long fkLorryID = resultSet.getLong("fkLorryID");
		String area = resultSet.getString("area");
		Long scheduleId = resultSet.getLong("id");
		return new Schedule(date, fkLorryID, area, scheduleId);
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM schedule");) {
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM schedule ORDER BY id DESC LIMIT 1");) {
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
						.prepareStatement("INSERT INTO schedule(id, driver_id, lorry_id, area, schedule_date) VALUES (?, ?, ?, ?, ?)");) {
			statement.setLong(1, schedule.getScheduleId());
			statement.setDate(5, schedule.getDate());
			statement.setLong(3, schedule.getFkLorryID());
			statement.setString(4, schedule.getArea());
			statement.setLong(2, schedule.getFkDriverID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Schedule read(Long scheduleId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM schedule WHERE scheduleID = ?");) {
			statement.setLong(1, scheduleId);
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


	@Override
	public Schedule update(Schedule schedule) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE schedule SET driver_id = ?, lorry_id = ?, area = ? WHERE id = ?");) {
			statement.setLong(1, schedule.getFkDriverID());
			statement.setLong(2, schedule.getFkLorryID());
			statement.setString(3, schedule.getArea());
			statement.setLong(4, schedule.getScheduleId());
			statement.executeUpdate();
			return read(schedule.getScheduleId());
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
	public int delete(long scheduleId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM schedule WHERE id = ?");) {
			statement.setLong(1, scheduleId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
