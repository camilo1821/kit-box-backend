package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido save(Pedido pedido);

    Pedido update(Long id, Pedido pedido);

    Page<Pedido> findAll(Pageable pageable);

    Pedido findById(Long id);

    List<Pedido> findByEstado(String estado);

    Page<Pedido> findByFecha(LocalDateTime fecha, Pageable pageable);

    void delete(Long id);
}
