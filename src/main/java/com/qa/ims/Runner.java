package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.DBUtils;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");

		Employee employee = new Employee();
		employee.checkEmployee();
		LOGGER.info("SO LONG!");
	}

}
