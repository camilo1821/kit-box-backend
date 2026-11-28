
package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.repository.ClienteRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceJPA implements ClienteService {


  private final ClienteRepository clienteRepository;
  private final PasswordEncoder passwordEncoder;

  public ClienteServiceJPA(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
    this.clienteRepository = clienteRepository;
      this.passwordEncoder = passwordEncoder;
  }

  // GUARDAR CLIENTE
  @Override
  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  // ACTUALIZAR CLIENTE
  /*@Override
  public Cliente update(Long id, Cliente cliente) {

    Optional<Cliente> clienteExistente = clienteRepository.findById(id);

    if (clienteExistente.isPresent()) {

      cliente.setId(id);

      return clienteRepository.save(cliente);
    }

    return null;
  }*/

  @Override
  public Cliente update(Long id, Cliente datosNuevos) {
    Optional<Cliente> optionalCliente = clienteRepository.findById(id);

    if (optionalCliente.isEmpty()) {
      return null;
    }

    Cliente clienteExistente = optionalCliente.get();

    if (datosNuevos.getNombre() != null && !datosNuevos.getNombre().isBlank()) {
      clienteExistente.setNombre(datosNuevos.getNombre().trim());
    }

    if (datosNuevos.getApellido() != null && !datosNuevos.getApellido().isBlank()) {
      clienteExistente.setApellido(datosNuevos.getApellido().trim());
    }

    if (datosNuevos.getCorreo() != null && !datosNuevos.getCorreo().isBlank()) {
      clienteExistente.setCorreo(datosNuevos.getCorreo().trim().toLowerCase());
    }

    if (datosNuevos.getTelefono() != null && !datosNuevos.getTelefono().isBlank()) {
      clienteExistente.setTelefono(datosNuevos.getTelefono().trim());
    }

    if (datosNuevos.getDireccion() != null && !datosNuevos.getDireccion().isBlank()) {
      clienteExistente.setDireccion(datosNuevos.getDireccion().trim());
    }

    if (datosNuevos.getRol() != null) {
      clienteExistente.setRol(datosNuevos.getRol());
    }

    // Solo actualiza y encripta la contraseña si el usuario envió una nueva
    if (datosNuevos.getContrasenia() != null && !datosNuevos.getContrasenia().isBlank()) {
      clienteExistente.setContrasenia(passwordEncoder.encode(datosNuevos.getContrasenia()));
    }

    return clienteRepository.save(clienteExistente);
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
