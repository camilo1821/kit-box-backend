package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.model.Pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidoDTO {

    private Long id;
    private double total_pedido;
    private String direccion_entrega;
    private LocalDateTime fecha_pedido;
    private Cliente cliente_id;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, double total_pedido,
                     String direccion_entrega,
                     LocalDateTime fecha_pedido,  Cliente cliente_id) {
        this.id = id;
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.cliente_id = cliente_id;
    }

    public static PedidoDTO fromEntity(Pedido pedido){
        return new PedidoDTO(
                pedido.getId(),
                pedido.getTotal_pedido(),
                pedido.getDireccion_entrega(),
                pedido.getFecha_pedido(),
                pedido.getCliente_id()
        );
    }

    public Pedido toEntity() {
        return new Pedido(this.total_pedido, this.direccion_entrega, this.fecha_pedido, this.cliente_id);
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal_pedido() {
        return total_pedido;
    }

    public void setTotal_pedido(double total_pedido) {
        this.total_pedido = total_pedido;
    }

    public String getDireccion_entrega() {
        return direccion_entrega;
    }

    public void setDireccion_entrega(String direccion_entrega) {
        this.direccion_entrega = direccion_entrega;
    }

    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }
}
