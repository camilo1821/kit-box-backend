package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido save(Pedido pedido);

    Pedido update(Long id, Pedido pedido);

    List<Pedido> findAll();

    Optional<Pedido> findById(Long id);

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByFecha(LocalDate fecha);

    void delete(Long id);
}
