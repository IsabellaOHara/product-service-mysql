package com.qa.product.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.product.entity.Product;
import com.qa.product.exception.ProductAlreadyExistsException;
import com.qa.product.exception.ProductNotFoundException;
import com.qa.product.service.ProductService;

@RestController
@RequestMapping("api/v1/product-service")
public class ProductController {

	@Autowired
	ProductService prodService;
	
	
	ResponseEntity<?> responseEntity;
	
	@PostMapping("/product")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
		Product createdProduct;  
		try {		
				
				createdProduct = this.prodService.saveProduct(product);
				
		} catch (ProductAlreadyExistsException e) {
			throw e;
		}
		responseEntity = new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
		return responseEntity;
	}
	
	@GetMapping("/product")
	public ResponseEntity<?> getAllEmployees() {
		return new ResponseEntity<>(this.prodService.getAllProduct(), HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
		Product product;
		try {
			product = this.prodService.getProductById(id);
		} catch (ProductNotFoundException e) {
			throw e;
		}
		return responseEntity;
	}
	
	@PutMapping("/employee")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) throws ProductNotFoundException {
		try {
			responseEntity = new ResponseEntity<>(prodService.updateProduct(product), HttpStatus.OK);
		} catch(ProductNotFoundException e) {
			throw e;
		}
		return responseEntity;
	}
	
	@DeleteMapping("/employee")
	public ResponseEntity<?> deleteProductById(@RequestParam("id") int id) throws ProductNotFoundException {
		boolean status;
		try {
			status = this.prodService.deleteProductById(id);
			responseEntity = new ResponseEntity<>("Product Deleted Successfully !!!", HttpStatus.OK);
		} catch(ProductNotFoundException e) {
			throw e;
		}
		return responseEntity;
	}
	
}