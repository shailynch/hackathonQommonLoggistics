package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

public class ProductController implements  CrudController<Product> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private ProductDAO productDAO;
	private Utils utils;

	public ProductController(ProductDAO productDAO, Utils utils) {
		this.productDAO = productDAO;
		this.utils = utils;
	}

	@Override
	public List<product> readAll() {
		List<Product> products = productDAO.readAll();
		for (Product Products : Products) {
			LOGGER.info(Product);
		}
		return Products;
		
		
	}

	@Override
	public product create() {
		LOGGER.info("Please enter an address");
		String address = utils.getString();
		LOGGER.info("Please enter a crate id");
		Long crateID =utils.getLong();
		LOGGER.info("Please enter a status");
		String status =utils.getString();
		
		Product products = productDAO.create(new Product(address, crateID, status));
		LOGGER.info("Product created");
		return products;
	}

	@Override
	public product update() {
		LOGGER.info("Please enter the product id you would like to update");
		Long productId = utils.getLong();
		LOGGER.info("Please enter the address to update");
		String address = utils.getString();
		LOGGER.info("Please enter the crate id");
		Long crateID = utils.getLong();
		LOGGER.info("Please enter the product status");
		String status = utils.getString();
		return product;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long productId = utils.getLong();
		return productDAO.delete(productid);
		
	}

	
	
}
