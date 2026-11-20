package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  void deleteById(Long id);

  Optional<Cliente> findByCorreo(String correo);

  Optional<Cliente> findById(Long id);

  List<Cliente> findAll();

  Cliente save(Cliente cliente);
}
