package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad {@link DetallePedido}.
 * <p>
 * Proporciona acceso a la base de datos para operaciones CRUD y consultas
 * como obtener todos los detalles de un pedido específico.
 */
@Repository
public interface DetallePedidoRepository  extends JpaRepository<DetallePedido,Long> {

    /**
     * Busca todos los detalles de un pedido por su identificador.
     * @param pedidoId identificador del pedido
     * @return lista de detalles asociados al pedido
     */
    List<DetallePedido> findByPedidoId(Long pedidoId);
}
