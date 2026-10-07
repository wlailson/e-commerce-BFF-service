package io.wlailson.github.e_commerce_BFF_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan
@OpenAPIDefinition(info = @Info(
		title = "E-commerce BFF API",
		version = "v1",
		description = "API agregadora dos serviços de identidade, catálogo, pedidos, pagamentos e análise."
))
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
