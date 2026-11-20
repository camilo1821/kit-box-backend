package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // PREFIJO + PROPIEDAD + PALABRA CLAVE

    List<Pedido> findByEstado(String estado);
    @Query("SELECT p FROM Pedido p WHERE p.fecha_pedido = :fecha")
    Page<Pedido> findByFecha(LocalDateTime fecha, Pageable pageable);

}
