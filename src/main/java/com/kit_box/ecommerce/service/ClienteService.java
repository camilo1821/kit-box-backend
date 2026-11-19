package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente cliente);

    Cliente update(Long id, Cliente cliente);

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findByEmail(String correo);

    void delete(Long id);
}
