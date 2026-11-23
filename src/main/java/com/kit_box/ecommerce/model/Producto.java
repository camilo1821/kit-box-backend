package com.kit_box.ecommerce.model;


import jakarta.persistence.*;
import java.util.*;

/**
 * Entidad que representa un producto disponible en la tienda de e-commerce.
 * <p>
 * Cada producto tiene información básica (nombre, descripción, precio, categoría),
 * control de inventario (stock), una imagen y un indicador para marcarlo como destacado.
 * <p>
 * Un producto puede aparecer en múltiples detalles de pedido a través de la relación
 * OneToMany con {@link DetallePedido}.
 * <p>
 * Mapeada a la tabla {@code producto} en la base de datos MySQL.
 */
@Entity
@Table(name="producto")
public class Producto {

    /** Identificador único del producto, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del producto (obligatorio). */
    @Column(nullable = false)
    private String nombre;

    /** Descripción detallada del producto (obligatoria). */
    @Column(nullable = false)
    private String descripcion;

    /** Precio unitario del producto en moneda local (obligatorio). */
    @Column(nullable = false)
    private double precio;

    /** Categoría a la que pertenece el producto (obligatoria). */
    @Column(nullable = false)
    private String categoria;

    /** Cantidad disponible en inventario (obligatorio). */
    @Column(nullable = false)
    private int stock;

    /** URL o ruta de la imagen del producto (obligatoria). */
    @Column(nullable = false)
    private String imagen;

    /** Indica si el producto debe mostrarse como destacado en la tienda. */
    @Column(nullable = false)
    private boolean destacado;

    /**
     * Lista de detalles de pedido que incluyen este producto.
     * Relación Lazy para evitar cargar todos los detalles al obtener un producto.
     */
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    /** Constructor por defecto requerido por JPA. */
    public Producto(){

    }

    /**
     * Constructor con todos los campos excepto id.
     *
     * @param nombre      nombre del producto
     * @param descripcion descripción detallada
     * @param precio      precio unitario
     * @param categoria   categoría del producto
     * @param stock       cantidad disponible en inventario
     * @param imagen      URL o ruta de la imagen
     * @param destacado   si se muestra como destacado
     */
    public Producto(String nombre, String descripcion, double precio, String categoria, int stock, String imagen, boolean destacado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.imagen = imagen;
        this.destacado= destacado;
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
