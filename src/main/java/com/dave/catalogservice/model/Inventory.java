package com.dave.catalogservice.model;

import lombok.Data;

@Data
public class Inventory {
	public Long id;
	public String productCode;
	public int availableQuantity;
}
