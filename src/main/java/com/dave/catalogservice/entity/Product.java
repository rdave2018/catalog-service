package com.dave.catalogservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	@Column(nullable= false)
	public String name;
	@Column
	public String description;
	@Column(nullable= false, unique = true)
	public String code;
	@Column
	public double price;
}
