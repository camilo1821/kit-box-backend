package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz de servicio para la gestión de pedidos.
 * <p>
 * Define las operaciones de negocio para crear, leer, actualizar y eliminar
 * pedidos, con soporte de paginación, búsqueda por estado y por fecha.
 */
public interface PedidoService {

    /**
     * Guarda un nuevo pedido, validando que el cliente exista.
     * @param pedido pedido a guardar
     * @return pedido guardado con el ID asignado
     * @throws IllegalArgumentException si el cliente no está especificado
     * @throws jakarta.persistence.EntityNotFoundException si el cliente no existe
     */
    Pedido save(Pedido pedido);

    /**
     * Actualiza los datos de un pedido existente.
     * @param id identificador del pedido a actualizar
     * @param pedido datos actualizados
     * @return pedido actualizado
     * @throws RuntimeException si no se encuentra el pedido
     */
    Pedido update(Long id, Pedido pedido);

    /**
     * Retorna todos los pedidos con paginación.
     * @param pageable parámetros de paginación
     * @return página de pedidos
     */
    Page<Pedido> findAll(Pageable pageable);

    /**
     * Busca un pedido por su identificador.
     * @param id identificador del pedido
     * @return pedido encontrado
     * @throws RuntimeException si no se encuentra el pedido
     */
    Pedido findById(Long id);

    /**
     * Busca pedidos por su estado actual.
     * @param estado estado del pedido
     * @return lista de pedidos con el estado especificado
     */
    List<Pedido> findByEstado(String estado);

    /**
     * Busca pedidos por fecha con paginación.
     * @param fecha fecha del pedido
     * @param pageable parámetros de paginación
     * @return página de pedidos que coinciden con la fecha
     */
    Page<Pedido> findByFecha(LocalDateTime fecha, Pageable pageable);

    /**
     * Elimina un pedido por su identificador.
     * @param id identificador del pedido a eliminar
     * @throws RuntimeException si no se encuentra el pedido
     */
    void delete(Long id);
}
