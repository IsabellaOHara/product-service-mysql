package com.qa.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.product.entity.Product;
import com.qa.product.exception.ProductAlreadyExistsException;
import com.qa.product.exception.ProductNotFoundException;
import com.qa.product.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository prodRepo;

	@Override
	public Product saveProduct(Product product) throws ProductAlreadyExistsException {
		Optional<Product> findByName = prodRepo.findByName(product.getName());
		if (findByName.isPresent()) {
			throw new ProductAlreadyExistsException();
		} else {
			return prodRepo.save(product);
		}

	}

	@Override
	public List<Product> getAllProduct() {
		return prodRepo.findAll();
	}

	@Override
	public Product getProductById(int id) throws ProductNotFoundException {
		Optional<Product> findById = prodRepo.findById(id);
		if(!findById.isPresent()) {
			throw new ProductNotFoundException();
		}
		else {
			return findById.get();
		}
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException  {
			Optional<Product> findById = prodRepo.findById(product.getId());
			if(!findById.isPresent()) {
				throw new ProductNotFoundException();
			}
			else {
				Product existingProd = findById.get();
				existingProd.setPrice(product.getPrice());
				return prodRepo.saveAndFlush(existingProd);
			}
	}

	@Override
	public boolean deleteProductById(int id) throws ProductNotFoundException {
		boolean status = false;
		Optional<Product> findById = prodRepo.findById(id);
		if(!findById.isPresent()) {
			throw new ProductNotFoundException();
		}
		else {
			prodRepo.delete(findById.get());
			status = true;
		}
		return status;
	}

}
