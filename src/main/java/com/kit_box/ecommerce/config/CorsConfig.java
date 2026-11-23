package com.kit_box.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS (Cross-Origin Resource Sharing) para la aplicación.
 * <p>
 * Permite que el frontend pueda hacer peticiones al backend desde un origen diferente.
 * Actualmente la configuración está vacía; se pueden agregar reglas en el método
 * {@code addCorsMappings} si es necesario.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Override este método para configurar las políticas CORS.
     * Ejemplo:
     * <pre>
     *   registry.addMapping("/api/**")
     *       .allowedOrigins("*")
     *       .allowedMethods("GET", "POST", "PUT", "DELETE");
     * </pre>
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuración CORS vacía — agregar reglas aquí si es necesario
    }
}
