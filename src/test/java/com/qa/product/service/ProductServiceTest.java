package com.qa.product.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.product.entity.Product;
import com.qa.product.exception.ProductAlreadyExistsException;
import com.qa.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository prodRepo;
	
	@Autowired
	@InjectMocks
	private ProductService prodService;
	
	Product prod1;
	Product prod2;
	Product prod3;
	
	List<Product> prodList;
	
	@BeforeEach
	public void setUp() {
		prod1 = new Product(1, "prod1", 10.00);
		prod2 = new Product(2, "prod2", 10.00);
		prod3 = new Product(3, "prod3", 10.00);
		prodList = Arrays.asList(prod1,prod2,prod3);
	}
	
	@AfterEach
	public void tearDown() {
		prod1 = prod2 = prod3 = null;
		prodRepo.deleteAll();
		prodList = null;
	}
	
	//getting an error here
	@Test
	@DisplayName("save-product-test")
	public void given_Product_To_Save_Should_Return_Saved_Product() throws ProductAlreadyExistsException {
		when(prodRepo.findByName(any())).thenReturn(null);
		when(prodRepo.save(any())).thenReturn(prod1);
		Product savedProduct = prodService.saveProduct(prod1);
		assertNotNull(savedProduct);
		assertEquals(1, savedProduct.getId());
		verify(prodRepo, times(1)).findByName(any());
		verify(prodRepo, times(1)).save(any());
		
	}
	
}
