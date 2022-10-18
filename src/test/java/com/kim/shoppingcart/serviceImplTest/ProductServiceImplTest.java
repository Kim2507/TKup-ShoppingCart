package com.kim.shoppingcart.serviceImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kim.shoppingcart.model.ProductDetails;
import com.kim.shoppingcart.repository.ProductRepository;
import com.kim.shoppingcart.service.impl.ProductServiceImpl;
/* For Product it is using Rest Repository so get/post(Read, Create) method 
 * already supported by Spring Rest*/
@SpringBootTest
public class ProductServiceImplTest {
	
	
	@Autowired
	ProductRepository productRepo;
	

	
	@Test
	public void testGetProducts() {
		//Optional<ProductDetails> btO = productService.findById(1);
		ProductDetails bt = productRepo.findById((long) 1).get();
		//Optional<ProductDetails> gtO = productService.findById(2);
		
		ProductDetails btE = new ProductDetails();
		btE.setName("Black Tea");
		assertEquals(btE.getName(),bt.getName());
		
	}
	

}
