package com.akhilfrancis.loans;

import com.akhilfrancis.loans.dto.LoansContactInfoDto;
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
/*@ComponentScans({ @ComponentScan("com.akhilfrancis.loans.controller") })
@EnableJpaRepositories("com.akhilfrancis.loans.repository")
@EntityScan("com.akhilfrancis.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(LoansContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Spring Boot Loans microservice REST API Documentation",
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
				description = "Spring Boot Loans microservice REST API Documentation",
				url = "https://www.akhilfrancis.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
