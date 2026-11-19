package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.dto.DetallePedidoDTO;
import com.kit_box.ecommerce.model.DetallePedido;
import com.kit_box.ecommerce.model.Pedido;
import com.kit_box.ecommerce.model.Producto;
import com.kit_box.ecommerce.repository.DetallePedidoRepository;
import com.kit_box.ecommerce.repository.PedidoRepository;
import com.kit_box.ecommerce.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetallePedidoServiceJPA implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    @Autowired
    public DetallePedidoServiceJPA(DetallePedidoRepository detallePedidoRepository,
                                   ProductoRepository productoRepository,
                                   PedidoRepository pedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
    }

   @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> listarTodos() {
        return detallePedidoRepository.findAll()
                .stream()
                .map(DetallePedidoDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> listarPorPedido(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new EntityNotFoundException("Pedido no encontrado con ID: " + pedidoId);
        }
        return detallePedidoRepository.findByPedidoId(pedidoId)
                .stream()
                .map(DetallePedidoDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePedidoDTO obtenerPorId(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de pedido no encontrado con ID: " + id));
        return DetallePedidoDTO.fromEntity(detalle);
    }

    @Override
    @Transactional
    public DetallePedidoDTO crearDetalle(Long pedidoId, DetallePedidoDTO dto) {
        if (dto == null || dto.getProductoId() == null) {
            throw new IllegalArgumentException("El ID del producto es obligatorio");
        }
        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con ID: " + pedidoId));

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + dto.getProductoId()));

        if (producto.getStock() < dto.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        double precioTotal = producto.getPrecio() * dto.getCantidad();

        DetallePedido nuevoDetalle = new DetallePedido(
                producto,
                pedido,
                dto.getCantidad(),
                precioTotal
        );

        DetallePedido guardado = detallePedidoRepository.save(nuevoDetalle);

        recalcularTotalPedido(pedido);

        return DetallePedidoDTO.fromEntity(guardado);
    }

    @Override
    @Transactional
    public DetallePedidoDTO actualizarCantidad(Long id, DetallePedidoDTO dto) {
        if (dto == null || dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de pedido no encontrado con ID: " + id));

        Producto producto = detalle.getProducto();
        if (producto.getStock() < dto.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioTotal(producto.getPrecio() * dto.getCantidad());

        DetallePedido actualizado = detallePedidoRepository.save(detalle);

        recalcularTotalPedido(detalle.getPedido());

        return DetallePedidoDTO.fromEntity(actualizado);
    }

    @Override
    @Transactional
    public void eliminarDetalle(Long id) {

        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de pedido no encontrado con ID: " + id));

        Pedido pedido = detalle.getPedido();

        detallePedidoRepository.delete(detalle);

        recalcularTotalPedido(pedido);
    }

    //BAck calcular total
    private void recalcularTotalPedido(Pedido pedido) {
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedido.getId());

        double total = detalles.stream()
                .mapToDouble(DetallePedido::getPrecioTotal)
                .sum();

        pedido.setTotal_pedido(total);
        pedidoRepository.save(pedido);
    }
}