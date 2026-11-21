package com.kit_box.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private double total_pedido;

    @Column(nullable=false)
    private String direccion_entrega;

    @Column(nullable=false)
    private LocalDateTime fecha_pedido;

    @Column(nullable=false)
    private String estado;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id", nullable = false)
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(double total_pedido, String direccion_entrega, LocalDateTime fecha_pedido, String estado, Cliente cliente) {
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
        this.cliente = cliente;
    }

/*public Pedido(double totalPedido, String direccionEntrega, LocalDateTime fechaPedido, String estado, Cliente cliente) {
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
        this.cliente = cliente;
    }*/

   /* public Pedido(Long id, double total_pedido, String direccion_entrega,
                  LocalDateTime fecha_pedido, Long cliente_id) {
        this.id = id;
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.cliente_id = cliente_id;
    }*/


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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
