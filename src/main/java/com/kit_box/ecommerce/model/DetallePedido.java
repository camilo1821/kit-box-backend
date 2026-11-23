package com.kit_box.ecommerce.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un detalle (línea) dentro de un pedido.
 * <p>
 * Cada detalle vincula un {@link Producto} con un {@link Pedido}, indicando
 * la cantidad solicitada y el precio total de esa línea (precio unitario × cantidad).
 * <p>
 * Un pedido puede tener múltiples detalles, y un producto puede aparecer en
 * varios pedidos a través de esta tabla intermedia.
 * <p>
 * Mapeada a la tabla {@code detalle_pedido} en la base de datos MySQL.
 */
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    /** Identificador único del detalle, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Long idDetallePedido;

    /**
     * Producto asociado a este detalle (obligatorio).
     * Relación ManyToOne con carga diferida (Lazy).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * Pedido al que pertenece este detalle (obligatorio).
     * Relación ManyToOne con carga diferida (Lazy).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /** Cantidad de unidades del producto en este detalle (obligatorio). */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Precio total de esta línea (precio unitario × cantidad).
     * Se calcula al crear o actualizar el detalle.
     */
    @Column(name = "precio_total", nullable = false)
    private double precioTotal;

    /** Constructor por defecto requerido por JPA. */
    public DetallePedido() {
    }

    /**
     * Constructor con todos los campos excepto el identificador.
     *
     * @param producto   producto asociado
     * @param pedido     pedido al que pertenece
     * @param cantidad   número de unidades
     * @param precioTotal precio total de la línea
     */
    public DetallePedido(Producto producto, Pedido pedido, int cantidad, double precioTotal) {
        this.producto = producto;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public Long getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}