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

	import com.qa.ims.persistence.domain.Driver;
import com.qa.ims.persistence.domain.Schedule;
import com.qa.ims.utils.DBUtils;

	public class ScheduleDAO implements Dao<Schedule> {

		public static final Logger LOGGER = LogManager.getLogger();

		@Override
		public Schedule modelFromResultSet(ResultSet resultSet) throws SQLException {
			Long fkDriverID = resultSet.getLong("fkDriverID");
			Date firstName = resultSet.getDate("date");
			Long fkLorryID = resultSet.getLong("fkLorryID");
			String area = resultSet.getString("area");
			Long scheduleId = resultSet.getLong("scheduleId");
			return new Driver(fkDriverID, date, fkLorryID, area, scheduleId);
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
				List<Driver> schedules = new ArrayList<>();
				while (resultSet.next()) {
					schedules.add(modelFromResultSet(resultSet));
				}
				return schedules;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
		}

		public Driver readLatest() {
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
				statement.setLong(1, schedule.getFkDriverID());
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
		public Driver read(Long driverID) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement("SELECT * FROM Driver WHERE driverID = ?");) {
				statement.setLong(1, driverID);
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
		 * Updates a Driver in the database
		 * 
		 * @param customer - takes in a customer object, the id field will be used to
		 *                 update that customer in the database
		 * @return
		 */
		@Override
		public Driver update(Driver driver) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE Driver SET firstname = ?, surname = ? WHERE driverID = ?");) {
				statement.setString(1, driver.getFirstName());
				statement.setString(2, driver.getSurname());
				statement.setLong(3, driver.getDriverID());
				statement.executeUpdate();
				return read(driver.getDriverID());
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return null;
		}

		/**
		 * Deletes a driver in the database
		 * 
		 * @param id - id of the customer
		 */
		@Override
		public int delete(long driverID) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement("DELETE FROM Driver WHERE driverID = ?");) {
				statement.setLong(1, driverID);
				return statement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return 0;
		}

	}

	
	
	
	
	
	
	
	
	
	
	

