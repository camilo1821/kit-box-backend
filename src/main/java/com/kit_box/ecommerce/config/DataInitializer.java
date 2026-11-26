package com.kit_box.ecommerce.config;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.model.Rol;
import com.kit_box.ecommerce.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception{
        if(clienteRepository.findByCorreo("admin@kitbox.com").isEmpty()){
            Cliente admin = new Cliente();
            admin.setNombre("Admin KitBox");
            admin.setCorreo("admin@kitbox.com");
            admin.setContrasenia(passwordEncoder.encode("123admin"));
            admin.setRol(Rol.ADMIN);
            clienteRepository.save(admin);
            System.out.println(clienteRepository.findByCorreo("Usuario admin creado: admin / 123456 "));

        }
    }
}
