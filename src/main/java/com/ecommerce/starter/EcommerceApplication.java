package com.ecommerce.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ecommerce.repository"})
@EntityScan(basePackages = {"com.ecommerce.model"})
@ComponentScan(basePackages = {"com.ecommerce"})
@OpenAPIDefinition(
	info = @Info(
		title = "E-Commerce API",
		version = "1.0",
		description = "E-Commerce REST API Documentation",
		contact = @Contact(
			name = "Your Name",
			email = "your.email@example.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.apache.org/licenses/LICENSE-2.0"
		)
	)
)
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
