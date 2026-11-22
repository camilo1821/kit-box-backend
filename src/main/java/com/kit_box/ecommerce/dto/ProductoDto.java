package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.Producto;

public class ProductoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private int stock;
    private String imagen;
    private boolean destacado;

    public ProductoDto(){

    }

    public ProductoDto(Long id, String nombre, String descripcion, double precio, String categoria, int stock, String imagen, boolean destacado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.imagen = imagen;
        this.destacado = destacado;
    }

    public static ProductoDto fromEntity(Producto producto){
        return new ProductoDto(
            producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCategoria(),
                producto.getStock(),
                producto.getImagen(),
                producto.isDestacado()
        );
    }

    public Producto toEntity(){
        return new Producto(this.nombre,this.descripcion,this.precio,this.categoria,this.stock,this.imagen, this.destacado);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }
}
