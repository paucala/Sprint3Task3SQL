package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.domain.Product;

public class ServicePruebas {

	List<Product> prodListPrueba = new ArrayList<>();
	
	public int createProduct(Product product) {

		prodListPrueba.add(product);
		
		prodListPrueba.forEach(x -> System.out.println(x));
		return 1;
	}
	
	public List<Product> getAllProducts(){
		return prodListPrueba;
	}
}
