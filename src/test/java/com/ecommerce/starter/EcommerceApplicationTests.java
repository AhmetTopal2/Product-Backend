package com.ecommerce.starter;

import com.ecommerce.model.Product;
import com.ecommerce.repository.IProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional // Veritabanını her testten sonra temizler
class ProductRepositoryTests {

	@Autowired
	private IProductRepository productRepository;

	
		
	
}
