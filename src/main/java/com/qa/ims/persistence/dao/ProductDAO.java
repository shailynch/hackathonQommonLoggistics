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

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

public class ProductDAO implements Dao<Product> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Product modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long productID = resultSet.getLong("id");
		Long crateID = resultSet.getLong("crate_id");
		String address = resultSet.getString("address");
		String status = resultSet.getString("delivery_status");
		Date lastUpdated = resultSet.getDate("last_updated");

		return new Product(productID, crateID, address, status, lastUpdated);
	}

	@Override
	public List<Product> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product");) {
			List<Product> products = new ArrayList<>();
			while (resultSet.next()) {
				products.add(modelFromResultSet(resultSet));
			}
			return products;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Product readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Product create(Product product) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO product(address, crate_id, delivery_status, last_updated) VALUES (?, ?, ?, ?)");) {
			statement.setString(1, product.getAddress());
			statement.setLong(2, product.getCrateID());
			statement.setString(3, product.getStatus());
			statement.setDate(4, product.getLastUpdated());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Product read(Long productID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");) {
			statement.setLong(1, productID);
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
	public Product update(Product product) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE product SET crate_id = ?, address = ? WHERE id = ?");) {
			statement.setLong(1, product.getCrateID());
			statement.setString(2, product.getAddress());
			statement.setLong(3, product.getProductID());
			statement.executeUpdate();
			return read(product.getProductID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long productID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");) {
			statement.setLong(1, productID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
