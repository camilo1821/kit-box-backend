package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImp implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Cliente> cliente = Optional.of(clienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado" + correo)));


        return User.builder()
                .username(cliente.get().getCorreo())
                .password(cliente.get().getContrasenia())
                .roles(cliente.get().getRol().name())
                .build();
    }
}
