package com.kit_box.ecommerce.dto;



public class DetallePedidoDTO {

    private Integer producto_id;
    private Integer cantidad;

    public DetallePedidoDTO() {

    }

    public DetallePedidoDTO(Integer producto_id,  Integer cantidad) {
        this.producto_id = producto_id;
        this.cantidad = cantidad;
    }

    public Integer getProducto_id() {
        return producto_id;
    }
    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
