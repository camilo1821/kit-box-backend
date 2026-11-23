package com.kit_box.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un pedido realizado por un cliente.
 * <p>
 * Un pedido contiene el total, la dirección de entrega, la fecha, el estado
 * y una referencia al cliente que lo realizó. Los productos del pedido se
 * gestionan a través de la entidad {@link DetallePedido}.
 * <p>
 * Estados típicos: "PENDIENTE", "EN_PROCESO", "ENVIADO", "ENTREGADO", "CANCELADO".
 * <p>
 * Mapeada a la tabla {@code pedido} en la base de datos MySQL.
 */
@Entity
@Table(name="pedido")
public class Pedido {

    /** Identificador único del pedido, generado automáticamente. */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** Monto total del pedido, calculado a partir de sus detalles. */
    @Column(nullable=false)
    private double total_pedido;

    /** Dirección de entrega del pedido (obligatoria). */
    @Column(nullable=false)
    private String direccion_entrega;

    /** Fecha y hora en que se realizó el pedido (obligatorio). */
    @Column(nullable=false)
    private LocalDateTime fecha_pedido;

    /**
     * Estado actual del pedido (obligatorio).
     * Valores típicos: PENDIENTE, EN_PROCESO, ENVIADO, ENTREGADO, CANCELADO.
     */
    @Column(nullable=false)
    private String estado;

    /**
     * Cliente que realizó el pedido (obligatorio).
     * Relación ManyToOne con carga diferida (Lazy).
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id", nullable = false)
    private Cliente cliente;

    /** Constructor por defecto requerido por JPA. */
    public Pedido() {
    }

    /**
     * Constructor con todos los campos excepto id.
     *
     * @param total_pedido     monto total del pedido
     * @param direccion_entrega dirección de entrega
     * @param fecha_pedido     fecha y hora del pedido
     * @param estado           estado actual (PENDIENTE, EN_PROCESO, etc.)
     * @param cliente          cliente que realizó el pedido
     */
    public Pedido(double total_pedido, String direccion_entrega, LocalDateTime fecha_pedido, String estado, Cliente cliente) {
        this.total_pedido = total_pedido;
        this.direccion_entrega = direccion_entrega;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
        this.cliente = cliente;
    }

    // ------------------------------------------------------------------
    // GETTERS & SETTERS
    // ------------------------------------------------------------------


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
