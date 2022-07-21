package com.qa.ims.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

public class ProductController implements CrudController<Product> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ProductDAO productDAO;
	private Utils utils;

	public ProductController(ProductDAO productDAO, Utils utils) {
		this.productDAO = productDAO;
		this.utils = utils;
	}

	@Override
	public List<Product> readAll() {
		List<Product> products = productDAO.readAll();
		for (Product product : products) {
			LOGGER.info(product);
		}
		return products;

	}

	@Override
	public Product create() {
		LOGGER.info("Please enter an address");
		String address = utils.getString();
		LOGGER.info("Please enter a crate id");
		Long crateID = utils.getLong();
		LOGGER.info("Please enter a status");
		String status = utils.getString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date lastUpdated = new Date(ts.getTime());
		Product products = productDAO.create(new Product(crateID, address, status, lastUpdated));
		LOGGER.info("Product created");
		return products;
	}

	@Override
	public Product update() {
		LOGGER.info("Please enter the product id you would like to update");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the address to update");
		String address = utils.getString();
		LOGGER.info("Please enter the crate id");
		Long crateID = utils.getLong();
		LOGGER.info("Please enter the product status");
		String status = utils.getString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date lastUpdated = new Date(ts.getTime());
		Product products = productDAO.update(new Product(productID, crateID, address, status, lastUpdated));
		return products;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long productID = utils.getLong();
		return productDAO.delete(productID);

	}

}
