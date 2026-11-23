package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.dto.DetallePedidoDTO;
import com.kit_box.ecommerce.model.DetallePedido;

import java.util.List;

/**
 * Interfaz de servicio para la gestión de detalles de pedido.
 * <p>
 * Define las operaciones para crear, leer, actualizar y eliminar
 * líneas de detalle dentro de un pedido, incluyendo la validación
 * de stock y el recálculo automático del total del pedido.
 */
public interface DetallePedidoService {

    /**
     * Retorna todos los detalles de pedido del sistema.
     * @return lista de todos los detalles
     */
    List<DetallePedidoDTO> listarTodos();

    /**
     * Retorna los detalles de un pedido específico.
     * @param pedidoId identificador del pedido
     * @return lista de detalles del pedido
     * @throws jakarta.persistence.EntityNotFoundException si el pedido no existe
     */
    List<DetallePedidoDTO> listarPorPedido(Long pedidoId);

    /**
     * Obtiene un detalle de pedido por su identificador.
     * @param id identificador del detalle
     * @return detalle encontrado
     * @throws jakarta.persistence.EntityNotFoundException si no se encuentra
     */
    DetallePedidoDTO obtenerPorId(Long id);

    /**
     * Crea un nuevo detalle para un pedido, validando stock disponible.
     * Recalcula automáticamente el total del pedido.
     * @param pedidoId identificador del pedido
     * @param dto datos del detalle a crear
     * @return detalle creado
     * @throws IllegalArgumentException si los datos son inválidos
     * @throws IllegalStateException si no hay stock suficiente
     */
    DetallePedidoDTO crearDetalle(Long pedidoId, DetallePedidoDTO dto);

    /**
     * Actualiza la cantidad de un detalle existente, validando stock.
     * Recalcula automáticamente el total del pedido.
     * @param id identificador del detalle
     * @param dto datos actualizados con la nueva cantidad
     * @return detalle actualizado
     * @throws IllegalArgumentException si la cantidad es inválida
     * @throws IllegalStateException si no hay stock suficiente
     */
    DetallePedidoDTO actualizarCantidad(Long id, DetallePedidoDTO dto);

    /**
     * Elimina un detalle de pedido y recalcula el total del pedido.
     * @param id identificador del detalle a eliminar
     * @throws jakarta.persistence.EntityNotFoundException si no se encuentra
     */
    void eliminarDetalle(Long id);
}
