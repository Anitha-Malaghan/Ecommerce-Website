package com.example.productservice_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// a proxy service is just a delegator
@SpringBootApplication
public class ProductServiceProxyApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductServiceProxyApplication.class, args);
	}

}
