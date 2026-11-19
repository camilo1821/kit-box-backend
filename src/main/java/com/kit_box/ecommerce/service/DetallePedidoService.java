package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.dto.DetallePedidoDTO;
import com.kit_box.ecommerce.model.DetallePedido;

import java.util.List;

public interface DetallePedidoService {

    List<DetallePedidoDTO> listarTodos();

    List<DetallePedidoDTO> listarPorPedido(Long pedidoId);

    DetallePedidoDTO obtenerPorId(Long id);

    DetallePedidoDTO crearDetalle(Long pedidoId, DetallePedidoDTO dto);

    DetallePedidoDTO actualizarCantidad(Long id, DetallePedidoDTO dto);

    void eliminarDetalle(Long id);
}
