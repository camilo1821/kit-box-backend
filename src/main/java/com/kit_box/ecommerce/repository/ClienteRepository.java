package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link Cliente}.
 * <p>
 * Proporciona acceso a la base de datos para operaciones CRUD y consultas
 * personalizadas como búsqueda por correo electrónico.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  /** Elimina un cliente por su identificador. */
  void deleteById(Long id);

  /**
   * Busca un cliente por su correo electrónico.
   * @param correo correo electrónico del cliente
   * @return Optional con el cliente si existe
   */
  Optional<Cliente> findByCorreo(String correo);

  /**
   * Busca un cliente por su identificador.
   * @param id identificador del cliente
   * @return Optional con el cliente si existe
   */
  Optional<Cliente> findById(Long id);

  /** Retorna todos los clientes registrados. */
  List<Cliente> findAll();

  /** Guarda o actualiza un cliente en la base de datos. */
  Cliente save(Cliente cliente);
}
