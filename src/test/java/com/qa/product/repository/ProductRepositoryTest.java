package com.qa.product.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.product.entity.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	ProductRepository prodRepo;
	
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
	
	@Test
	@DisplayName("save-product-test")
	@Disabled
	public void given_Product_To_Save_Should_Return_The_Saved_Product() {
		prodRepo.save(prod1);
		Product savedProduct = prodRepo.findById(prod1.getId()).get();
		assertNotNull(savedProduct);
		assertEquals("prod1", savedProduct.getName());
		
	}
	
	@Test
	@DisplayName("get-product-list-test")
	@Disabled
	public void given_GetAllProducts_Should_Return_Product_List() {
		prodRepo.save(prod1);
		prodRepo.save(prod2);
		prodRepo.save(prod3);
		
		List<Product> prodList = prodRepo.findAll();
		assertEquals(3, prodList.size());
		assertEquals("prod1", prodList.get(0).getName());
	}
	
	@Test
	@DisplayName("get-product-not-existing-id-test")
	public void given_Not_Existing_Id_Should_Return_Optional_Empty() {
		prodRepo.save(prod1);
		assertThat(prodRepo.findById(2)).isEmpty();
	}
	
	
}
