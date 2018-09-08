package com.dave.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dave.catalogservice.Service.ProductService;
import com.dave.catalogservice.entity.Product;
import com.dave.catalogservice.exception.ProductNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
	private final ProductService productService;
	 
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("")
    public List<Product> getProducts(){
    	return (List<Product>) productService.findAllProducts();
    }
    
    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable String code) {
    	return productService.findProductByCode(code)
    			.orElseThrow(()-> new ProductNotFoundException("Product with code ["+code+"] doesn't exist"));
    }
}
