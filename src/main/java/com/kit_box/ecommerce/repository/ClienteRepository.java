package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
  void deleteById(Long id);

  Optional<Cliente> findByCorreo(String correo);

  Optional<Cliente> findById(Long id);

  List<Cliente> findAll();

  Cliente save(Cliente cliente);
}
