package com.qa.product.service;

import java.util.List;

import com.qa.product.entity.Product;
import com.qa.product.exception.ProductAlreadyExistsException;
import com.qa.product.exception.ProductNotFoundException;

public interface IProductService {

	//CRUD operations
	public Product saveProduct(Product product) throws ProductAlreadyExistsException;
	public List<Product> getAllProduct();
	public Product getProductById(int id) throws ProductNotFoundException;
	public Product updateProduct(Product product) throws ProductNotFoundException;
	public boolean deleteProductById(int id) throws ProductNotFoundException;
	
	
}
