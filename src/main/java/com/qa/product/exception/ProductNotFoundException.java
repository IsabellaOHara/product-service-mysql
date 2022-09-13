package com.qa.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product doesn't exist with this ID")
public class ProductNotFoundException extends Exception {

}
