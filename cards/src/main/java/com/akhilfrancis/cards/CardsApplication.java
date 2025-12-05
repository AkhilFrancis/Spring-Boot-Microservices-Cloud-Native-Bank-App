package com.akhilfrancis.cards;

import com.akhilfrancis.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.akhilfrancis.cards.controller") })
@EnableJpaRepositories("com.akhilfrancis.cards.repository")
@EntityScan("com.akhilfrancis.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Spring Boot Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Akhil Francis",
						email = "tutor@akhilfrancis.com",
						url = "https://www.akhilfrancis.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.akhilfrancis.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Cards microservice REST API Documentation",
				url = "https://www.akhilfrancis.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
