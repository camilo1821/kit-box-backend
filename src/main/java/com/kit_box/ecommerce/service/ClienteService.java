package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente cliente);

    Cliente update(Long id, Cliente cliente);

    Page<Cliente> findAll(Pageable pageable);

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findByEmail(String correo);

    void delete(Long id);
}
