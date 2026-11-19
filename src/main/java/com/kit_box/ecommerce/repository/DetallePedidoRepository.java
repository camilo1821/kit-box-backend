package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.DetallePedido;
import com.kit_box.ecommerce.service.DetallePedidoServiceJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepository  extends JpaRepository<DetallePedido,Long> {

}
