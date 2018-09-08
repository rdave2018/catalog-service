package com.dave.catalogservice.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dave.catalogservice.entity.Product;
import com.dave.catalogservice.model.Inventory;
import com.dave.catalogservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductService {

	private final ProductRepository repo;
	private final RestTemplate template;
	
	@Autowired
	public ProductService(ProductRepository repo, RestTemplate template) {
		this.repo = repo;
		this.template = template;
	}
	
	public Iterable<Product> findAllProducts() {
        return repo.findAll();
    }
 
    public Optional<Product> findProductByCode(String code) {
    	Optional<Product> productOptional = repo.findByCode(code);
    	if(productOptional.isPresent()) {
            //log.info("Fetching inventory level for product_code: "+code);
            ResponseEntity<Inventory> itemResponseEntity =
                    template.getForEntity("http://inventory-service/api/inventory/{code}",
                                                Inventory.class,
                                                code);
            if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
                Integer quantity = itemResponseEntity.getBody().availableQuantity;
                //log.info("Available quantity: "+quantity);
                productOptional.get().price = quantity;
            } else {
                //log.error("Unable to get inventory level for product_code: "+code +
                //", StatusCode: "+itemResponseEntity.getStatusCode());
            }
        }
        return productOptional;
    }
}
