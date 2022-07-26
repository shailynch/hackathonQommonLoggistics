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

import com.qa.ims.persistence.domain.Crate;
import com.qa.ims.utils.DBUtils;

public class CrateDAO implements Dao<Crate> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Crate modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long fkScheduleID = resultSet.getLong("schedule_id");
		String area = resultSet.getString("area");
		return new Crate(id, fkScheduleID, area);
	}

	/**
	 * Reads all crates from the database
	 * 
	 * @return A list of crates
	 */
	@Override
	public List<Crate> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM crate");) {
			List<Crate> crates = new ArrayList<>();
			while (resultSet.next()) {
				crates.add(modelFromResultSet(resultSet));
			}
			return crates;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Crate readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM crate ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a crate in the database
	 * 
	 * @param crate - takes in a crate object. id will be ignored
	 */
	@Override
	public Crate create(Crate crate) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO crate(schedule_id, area) VALUES (?, ?)");) {
			statement.setLong(1, crate.getFkScheduleId());
			statement.setString(2, crate.getArea());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Crate read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM crate WHERE id = ?");) {
			statement.setLong(1, id);
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
	 * Updates a crate in the database
	 * 
	 * @param crate - takes in a crate object, the id field will be used to update
	 *              that crate in the database
	 * @return
	 */
	@Override
	public Crate update(Crate crate) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE crate SET schedule_id = ?, area = ? WHERE id = ?");) {
			statement.setLong(1, crate.getFkScheduleId());
			statement.setString(2, crate.getArea());
			statement.setLong(3, (crate.getCrateId()));
			statement.executeUpdate();
			return read(crate.getCrateId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a crate in the database
	 * 
	 * @param id - id of the crate
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM crate WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
