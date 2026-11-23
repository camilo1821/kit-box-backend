package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.model.Pedido;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para la entidad {@link Pedido}.
 * <p>
 * Usa solo el ID del cliente (clienteId) en lugar del objeto completo
 * para evitar referencias circulares en la serialización JSON.
 */
public class PedidoDTO {

    private Long id;
    private double total_pedido;
    private String direccion_entrega;
    private LocalDateTime fecha_pedido;
    private String estado;
    private Long clienteId;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, double total_pedido, String direccion_entrega,
                     LocalDateTime fecha_pedido, String estado, Long clienteId) {
        this.id = id;
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    /** Convierte una entidad Pedido a su DTO, extrayendo solo el ID del cliente para evitar ciclos. */
    public static PedidoDTO fromEntity(Pedido pedido) {
        Long idDelCliente = (pedido.getCliente() != null) ? pedido.getCliente().getId() : null;
        return new PedidoDTO(
                pedido.getId(),
                pedido.getTotal_pedido(),
                pedido.getDireccion_entrega(),
                pedido.getFecha_pedido(),
                pedido.getEstado(),
                idDelCliente
        );
    }

    /** Convierte este DTO a una entidad Pedido, creando un Cliente temporal con solo su ID. */
    public Pedido toEntity() {
        Cliente clienteTemp = null;
        if (this.clienteId != null) {
            clienteTemp = new Cliente();
            clienteTemp.setId(this.clienteId);
        }
        return new Pedido(
                this.total_pedido,
                this.direccion_entrega,
                this.fecha_pedido != null ? this.fecha_pedido : LocalDateTime.now(),
                this.estado != null ? this.estado : "PENDIENTE",
                clienteTemp
        );
    }

    // GETTERS Y SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTotal_pedido() { return total_pedido; }
    public void setTotal_pedido(double total_pedido) { this.total_pedido = total_pedido; }

    public String getDireccion_entrega() { return direccion_entrega; }
    public void setDireccion_entrega(String direccion_entrega) { this.direccion_entrega = direccion_entrega; }

    public LocalDateTime getFecha_pedido() { return fecha_pedido; }
    public void setFecha_pedido(LocalDateTime fecha_pedido) { this.fecha_pedido = fecha_pedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}