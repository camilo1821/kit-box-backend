package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.DetallePedido;

public class DetallePedidoDTO {

    private Long productoId;
    private Integer cantidad;

    public DetallePedidoDTO() {
    }

    public DetallePedidoDTO(Long productoId, Integer cantidad) {
        this.productoId= productoId;
        this.cantidad = cantidad;
    }

    public static DetallePedidoDTO fromEntity(DetallePedido detallePedido) {
        //Validacion para pedido null
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
