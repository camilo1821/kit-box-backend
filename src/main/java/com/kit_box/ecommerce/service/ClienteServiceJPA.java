
package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.repository.ClienteRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceJPA implements ClienteService {


  private final ClienteRepository clienteRepository;

  public ClienteServiceJPA(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  // GUARDAR CLIENTE
  @Override
  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  // ACTUALIZAR CLIENTE
  @Override
  public Cliente update(Long id, Cliente cliente) {

    Optional<Cliente> clienteExistente = clienteRepository.findById(id);

    if (clienteExistente.isPresent()) {

      cliente.setId(id);

      return clienteRepository.save(cliente);
    }

    return null;
  }

  // CONSULTAR TODOS LOS CLIENTES
  @Override
  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  // BUSCAR CLIENTE POR ID
  @Override
  public Optional<Cliente> findById(Long id) {
    return clienteRepository.findById(id);
  }

  // BUSCAR CLIENTE POR CORREO
  @Override
  public Optional<Cliente> findByEmail(String correo) {
    return clienteRepository.findByCorreo(correo);
  }

  // ELIMINAR CLIENTE
  @Override
  public void delete(Long id) {
    clienteRepository.deleteById(id);
  }
}
