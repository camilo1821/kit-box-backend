package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.DetallePedido;

/**
 * DTO (Data Transfer Object) para la entidad {@link DetallePedido}.
 * <p>
 * Contiene solo el ID del producto y la cantidad, evitando cargar
 * las entidades completas de Producto y Pedido en las respuestas JSON.
 */
public class DetallePedidoDTO {

    /** ID del producto asociado a este detalle. */
    private Long productoId;
    /** Cantidad de unidades del producto en este detalle. */
    private Integer cantidad;

    public DetallePedidoDTO() {
    }

    public DetallePedidoDTO(Long productoId, Integer cantidad) {
        this.productoId= productoId;
        this.cantidad = cantidad;
    }

    /**
     * Convierte una entidad DetallePedido a su DTO correspondiente.
     * Maneja nulos para evitar excepciones si el producto no está cargado.
     *
     * @param detallePedido entidad a convertir
     * @return DTO con el ID del producto y la cantidad
     */
    public static DetallePedidoDTO fromEntity(DetallePedido detallePedido) {
        if (detallePedido == null) {
            return null;
        }
        Long prodId = (detallePedido.getProducto() != null)
                ? detallePedido.getProducto().getId()
                : null;
        return new DetallePedidoDTO(
                prodId,
                detallePedido.getCantidad()
        );
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId= productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
