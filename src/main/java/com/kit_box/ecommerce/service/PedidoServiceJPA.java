package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.model.Pedido;
import com.kit_box.ecommerce.repository.ClienteRepository;
import com.kit_box.ecommerce.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de pedidos usando Spring Data JPA.
 * <p>
 * Gestiona la creación, actualización, consulta y eliminación de pedidos,
 * validando que el cliente exista antes de guardar un pedido y marcándose
 * como {@link Primary} para ser la implementación por defecto.
 */
@Service
@Primary
public class PedidoServiceJPA implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Constructor con inyección de dependencias.
     * @param pedidoRepository repositorio de pedidos
     * @param clienteRepository repositorio de clientes para validación
     */
    @Autowired
    public PedidoServiceJPA(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con Id: " + id));
    }

    @Override
    public List<Pedido> findByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    @Override
    public Page<Pedido> findByFecha(LocalDateTime fecha, Pageable pageable) {
        return pedidoRepository.findByFecha(fecha, pageable);
    }

    @Override
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado con Id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    /**
     * Guarda un nuevo pedido tras validar que el cliente asociado existe.
     * Carga el cliente real desde la base de datos para mantener la integridad referencial.
     */
    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }

        Cliente clienteReal = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + pedido.getCliente().getId()));

        pedido.setCliente(clienteReal);
        return pedidoRepository.save(pedido);
    }

    /**
     * Actualiza un pedido existente preservando su ID original.
     */
    @Override
    @Transactional
    public Pedido update(Long id, Pedido pedidoActualizado) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con Id: " + id));

        pedidoActualizado.setId(id);
        return pedidoRepository.save(pedidoActualizado);
    }


}
