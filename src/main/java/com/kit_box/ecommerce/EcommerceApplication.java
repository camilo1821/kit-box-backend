package com.kit_box.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Kit-Box E-commerce.
 * <p>
 * Punto de entrada del backend Spring Boot. Al ejecutar esta clase,
 * se inicia el servidor embebido y se configuran todos los componentes
 * del sistema (controladores, servicios, repositorios).
 */
@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
