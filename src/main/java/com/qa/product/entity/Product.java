package com.qa.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product_details")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;
	
	@NotNull
//	@Pattern(regexp = "^[A-Za-z0-9]*", message = "Invalid name, must only contain alphanumeric characters")
	@Column(name = "product_name")
	private String name;
	
	@NotNull
//	@Pattern(regexp = "([0-9]\\d{10}+)\\.([0-9]\\d{2})$", message = "Invalid price")
	@Column(name = "product_price")
	private double price;
	
	
	
	
}
