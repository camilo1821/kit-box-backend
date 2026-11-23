
package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de clientes usando Spring Data JPA.
 * <p>
 * Delega todas las operaciones al {@link ClienteRepository} y aplica
 * la lógica de negocio para actualizar clientes existentes.
 */
@Service
public class ClienteServiceJPA implements ClienteService {

  private final ClienteRepository clienteRepository;

  /**
   * Constructor con inyección de dependencias.
   * @param clienteRepository repositorio de clientes
   */
  public ClienteServiceJPA(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @Override
  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Override
  public Cliente update(Long id, Cliente cliente) {

    Optional<Cliente> clienteExistente = clienteRepository.findById(id);

    if (clienteExistente.isPresent()) {

      cliente.setId(id);

      return clienteRepository.save(cliente);
    }

    return null;
  }

  @Override
  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  @Override
  public Optional<Cliente> findById(Long id) {
    return clienteRepository.findById(id);
  }

  @Override
  public Optional<Cliente> findByEmail(String correo) {
    return clienteRepository.findByCorreo(correo);
  }

  @Override
  public void delete(Long id) {
    clienteRepository.deleteById(id);
  }
}
