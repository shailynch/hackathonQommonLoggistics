package com.qa.ims;

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

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;
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
				String driverChoice = "";
				LOGGER.info("enter checklist for your checklist or manage to amend your schedule, crate or products.");
				driverChoice = utils.getString();
				if (driverChoice.contains("checklist")) {
					checklist();
				} else if (driverChoice.contains("manage")) {
					Logistics logistics = new Logistics();
					logistics.employeeLogisticSystem();
				} else {
					LOGGER.info("incorrect option");
				}
			}

		} while (!employeeType.contains("exit"));
	}

	public void checklist() {

		Long driverID = null;
		do {
			driverID = promptID();
		} while (driverID == null);
		List<Product> products = read(driverID);
		for (Product product : products) {
			LOGGER.info(product);
		}
	}

	public Long promptID() {
		LOGGER.info("enter your driver id");
		return utils.getLong();
	}

	public List<Product> read(Long driverID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM product RIGHT JOIN crate ON crate_id = crate.id RIGHT JOIN schedule ON schedule_id = schedule.id where driver_id = ?");) {
			statement.setLong(1, driverID);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				List<Product> products = new ArrayList<>();
				while (resultSet.next()) {
					products.add(modelFromResultSetList(resultSet));
				}
				return products;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<Product> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM product RIGHT JOIN crate ON crate_id = crate.id RIGHT JOIN schedule ON schedule_id = schedule.id where driver_id = ?");) {

			List<Product> products = new ArrayList<>();
			while (resultSet.next()) {
				products.add(modelFromResultSetList(resultSet));
			}
			return products;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Product modelFromResultSetList(ResultSet resultSet) throws SQLException {
		Long productID = resultSet.getLong("id");
		Long crateID = resultSet.getLong("crate_id");
		String address = resultSet.getString("address");
		String status = resultSet.getString("delivery_status");
		Date lastUpdated = resultSet.getDate("last_updated");
		return new Product(productID, crateID, address, status, lastUpdated);
	}

	public String modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long productID = resultSet.getLong("id");
		Long crateID = resultSet.getLong("crate_id");
		String address = resultSet.getString("address");
		String status = resultSet.getString("delivery_status");
		Date lastUpdated = resultSet.getDate("last_updated");
		return new Product(productID, crateID, address, status, lastUpdated).toString();
	}

}