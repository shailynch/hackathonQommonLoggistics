package com.qa.ims.persistence.dao;

	


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;

	import com.qa.ims.persistence.domain.Driver;
	import com.qa.ims.utils.DBUtils;

	public class DriverDAO implements Dao<Driver> {

		public static final Logger LOGGER = LogManager.getLogger();

		@Override
		public Driver modelFromResultSet(ResultSet resultSet) throws SQLException {
			Long driverID = resultSet.getLong("driverID");
			String firstName = resultSet.getString("firstName");
			String surname = resultSet.getString("surname");
			return new Driver(driverID, firstName, surname);
		}

		/**
		 * Reads all customers from the database
		 * 
		 * @return A list of customers
		 */
		@Override
		public List<Driver> readAll() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM Driver");) {
				List<Driver> drivers = new ArrayList<>();
				while (resultSet.next()) {
					drivers.add(modelFromResultSet(resultSet));
				}
				return drivers;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
		}

		public Driver readLatest() {
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM Driver ORDER BY id DESC LIMIT 1");) {
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
		public Driver create(Driver driver) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)");) {
				statement.setString(1, driver.getFirstName());
				statement.setString(2, driver.getSurname());
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

	
	
	
	
	
	
	
	
	
	
	

