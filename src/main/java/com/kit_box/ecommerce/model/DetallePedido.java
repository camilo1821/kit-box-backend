package com.kit_box.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name="detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producto_id", nullable=false)
    private Long producto_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="pedido_id", nullable=false)
    private Long pedido_id;

    @Column(name="cantidad", nullable=false)
    private int cantidad;

    @Column(name="precio_total", nullable=false)
    private double precio_total;

    public DetallePedido() {
    }

    public DetallePedido(Long producto_id, Long pedido_id, int cantidad, double precio_total) {
        this.producto_id = producto_id;
        this.pedido_id = pedido_id;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
    }

    public Long getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(Long id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public Long getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Long pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
}
