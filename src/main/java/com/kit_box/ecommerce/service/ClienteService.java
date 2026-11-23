package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de clientes.
 * <p>
 * Define las operaciones de negocio para crear, leer, actualizar y eliminar
 * clientes, así como búsquedas por ID y correo electrónico.
 */
public interface ClienteService  {

    /**
     * Guarda un nuevo cliente en la base de datos.
     * @param cliente cliente a guardar
     * @return cliente guardado con el ID asignado
     */
    Cliente save(Cliente cliente);

    /**
     * Actualiza los datos de un cliente existente.
     * @param id identificador del cliente a actualizar
     * @param cliente datos actualizados
     * @return cliente actualizado, o null si no se encontró
     */
    Cliente update(Long id, Cliente cliente);

    /**
     * Retorna todos los clientes registrados.
     * @return lista de todos los clientes
     */
    List<Cliente> findAll();

    /**
     * Busca un cliente por su identificador.
     * @param id identificador del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findById(Long id);

    /**
     * Busca un cliente por su correo electrónico.
     * @param correo correo electrónico del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> findByEmail(String correo);

    /**
     * Elimina un cliente por su identificador.
     * @param id identificador del cliente a eliminar
     */
    void delete(Long id);
}
